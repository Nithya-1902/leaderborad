package com.playgame;

import com.dbcon.DBConn;

public class Score {
	public static void updateScore(String TeamA, String TeamB, String Winner) {
		String sql = "";
			if (Winner.equals(GameConstants.TeamA)) {
				sql = "update Game set wins=wins+1,score=score+3 where team_name='" + TeamA + "'";
				DBConn.performDBOperation(sql, DBConn.UPDATE);
				sql = "update Game set losses=losses+1 where team_name='" + TeamB + "'";
				DBConn.performDBOperation(sql, DBConn.UPDATE);
			} else if (Winner.equals(GameConstants.TeamB)) {
				sql = "update Game set wins=wins+1,score=score+3 where team_name='" + TeamB + "'";
				DBConn.performDBOperation(sql, DBConn.UPDATE);
				sql = "update Game set losses=losses+1 where team_name='" + TeamA + "'";
				DBConn.performDBOperation(sql, DBConn.UPDATE);
			} else if (Winner.equals(GameConstants.Tied)) {
				sql = "update Game set score=score+2,ties=ties+1 where team_name='" + TeamB + "'";
				DBConn.performDBOperation(sql, DBConn.UPDATE);
				sql = "update Game set score=score+2,ties=ties+1 where team_name='" + TeamA + "'";
				DBConn.performDBOperation(sql, DBConn.UPDATE);
			}
	}
}
