package com.zeffee.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zeffee.dao.VoteDAO;
import com.zeffee.entity.Votes;
import com.zeffee.exception.InvalidStatusException;
import com.zeffee.exception.ServerException;
import com.zeffee.lib.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Zeffee on 2017/7/15.
 */
@RestController
@Transactional
public class VoteController {
    @Autowired
    private VoteDAO dao;

    @Autowired
    private HttpSession session;

    @ApiOperation(value = "参与投票", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/takeVote", method = RequestMethod.POST)
    public Map<String, Object> vote(@ApiParam(value = "投票项目的tid和用户投的选项oid", defaultValue = "{\"tid\":30,\"oid\":[3,4]}") @RequestBody Map<String, Object> params) {
        if (!(params.get("tid") instanceof Integer)) return Common.getResponseMap(500, "[tid] is invaild!");
        if (!(params.get("oid") instanceof ArrayList)) return Common.getResponseMap(500, "[oid] is invaild!");

        int tid = (int) params.get("tid");
        List user_vote_oid_list = (List) params.get("oid");
        String uid = session.getAttribute("openid").toString();

        checkVoted_IfNotThrowException(uid, tid);

        // check time and per vote and oid_list valid
        Map<String, Object> dataMap = dao.getTime_PerVote_OidListByTid(tid);
        if (dataMap == null) return Common.getResponseMap(500, "[tid] is not survivable!");
        int votes_per_user = (byte) dataMap.get("votes_per_user") & 0xFF;
        List theme_oid_list = Common.toListFromJson(dataMap.get("oid_list").toString());

        Date nowDate = new Date();
        if (nowDate.before((Date) dataMap.get("start_time")) || nowDate.after((Date) dataMap.get("end_time")))
            return Common.getResponseMap(500, "Invaild Vote Time!");
        if (user_vote_oid_list.size() > votes_per_user) return Common.getResponseMap(500, "Invaild Vote Size!");
        if (!theme_oid_list.containsAll(user_vote_oid_list)) return Common.getResponseMap(500, "Invaild OidList!");


        saveVoteRecordAndIncrementOptionCount(user_vote_oid_list, tid, uid);
        dao.incrementThemeCountByTid(tid, 1);

        return Common.getResponseMap(200);
    }

    @ApiOperation(value = "检查是否投过票")
    @RequestMapping(value = "/checkVoted/{tid}", method = RequestMethod.GET)
    public Map<String, Object> checkVoted(@ApiParam(value = "投票项目的tid", defaultValue = "29") @PathVariable(value = "tid") int tid) {
        checkVoted_IfNotThrowException((String) session.getAttribute("openid"), tid);

        return Common.getResponseMap(200);
    }


    //======= Function =======

    private void checkVoted_IfNotThrowException(String uid, int tid) {
        int vote_record_counts = dao.getSelfVoteRecordByTid(uid, tid);
        if (vote_record_counts > 0) throw new InvalidStatusException("Have voted already");
    }

    private void saveVoteRecordAndIncrementOptionCount(List<Integer> oidList, int tid, String uid) {
        for (Integer oid : oidList) {
            dao.saveVoteRecord(new Votes(uid, oid, tid));

            if (1 != dao.incrementOptionCountByOid(oid)) {
                throw new ServerException("increment option count went wrong! database down?");
            }
        }
    }
}
