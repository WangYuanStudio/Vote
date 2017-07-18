package com.zeffee.controller;

import com.zeffee.dao.VoteDAO;
import com.zeffee.entity.Votes;
import com.zeffee.exception.InvalidStatusException;
import com.zeffee.exception.ServerException;
import com.zeffee.lib.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public Map<String, Object> vote(@RequestBody Map<String, Object> params, HttpSession session) {
        if (!(params.get("tid") instanceof Integer)) return Common.getResponseMap(500, "[tid] is invaild!");
        if (!(params.get("oid") instanceof ArrayList)) return Common.getResponseMap(500, "[oid] is invaild!");

        int tid = (int) params.get("tid");
        List user_vote_oid_list = (List) params.get("oid");
        String uid = session.getAttribute("openid").toString();

        checkVoted_IfNotThrowException(uid, tid);

        // check per vote and oid_list valid
        Map<String, Object> dataMap = dao.getPerVoteAndOidListByTid(tid);
        if (dataMap == null) return Common.getResponseMap(500, "[tid] is not survivable!");
        int votes_per_user = (byte) dataMap.get("votes_per_user") & 0xFF;
        List theme_oid_list = Common.toListFromJson(dataMap.get("oid_list").toString());

        if (user_vote_oid_list.size() > votes_per_user
                || !theme_oid_list.containsAll(user_vote_oid_list))
            return Common.getResponseMap(500, "Invaild Operation!");


        saveVoteRecordAndIncrementOptionCount(user_vote_oid_list, tid, uid);
        dao.incrementThemeCountByTid(tid, 1);

        return Common.getResponseMap(200);
    }

    @RequestMapping(value = "/checkVoted/{tid}", method = RequestMethod.GET)
    public Map<String, Object> checkVoted(@PathVariable(value = "tid") int tid, HttpSession session) {
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