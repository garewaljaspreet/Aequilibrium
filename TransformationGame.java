package com.example.navde.myapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Jaspreet
 * This class represents logic to calculate number of battles, info about winning and losing team.
 * We can pass group of transformsers here and result will get printed.
 */

public class TransformationGame {
    private int PLAYER_TYPE_AUTO=3,PLAYER_TYPE_DEC=4,TYPE_WIN=1,TYPE_LOSE=0,MATCH_TIE=5;
    private ArrayList<BeansPlayer> teamAutobot=new ArrayList<>();// Used to store Autobots
    private ArrayList<BeansPlayer> teamDecepticon=new ArrayList<>();// Used to store Decepticons

    /**
     * This is entry point for this class. This method is responsible for storing Autobots and Decepticons in two different lists.
     * Here we are assuming that provided group will contain 'A' or 'D' in type field for Autobota and Decepticons resp in model object.
     * @param group Arraylist of transformers to process
     */
    private void startProcess(ArrayList<BeansPlayer> group)
    {
        for (int i=0;i<group.size();i++)
        {
            if(group.get(i).getType().equals("A"))// Checks if Player is Autobot or Decepticon and store in resp list
            {
                teamAutobot.add(group.get(i));
            }
            else
            {
                teamDecepticon.add(group.get(i));
            }
        }

        // Here sorting is done based on provided formula (Strength + Intelligence + Speed + Endurance + Firepower)
        Comparator<BeansPlayer> objComparator = new Comparator<BeansPlayer>() {
            public int compare(BeansPlayer o1, BeansPlayer o2) {
                int no1 = o1.getCourage()+o1.getEndurance()+o1.getFirepower()+o1.getIntelligence()+o1.getRank()+o1.getSkill()+o1.getSpeed()+o1.getStrength();
                int no2 = o2.getCourage()+o2.getEndurance()+o2.getFirepower()+o2.getIntelligence()+o2.getRank()+o2.getSkill()+o2.getSpeed()+o2.getStrength();
                return  no1 > no2 ? -1 : no1 == no2 ? 0 : 1;
            }
        };
        Collections.sort(teamAutobot, objComparator);//Sorting autobot list
        Collections.sort(teamDecepticon, objComparator);//Sorting decepticon list

        int numBattles;
        numBattles=(teamAutobot.size()>teamDecepticon.size())?teamAutobot.size():teamDecepticon.size();

        getResults(teamAutobot,teamDecepticon,numBattles);
    }

    /**
     * This method is responsible for calculating results based upon provided conditions
     * @param teamAutobot sorted Arraylist of team autobots
     * @param teamDecepticon sorted Arraylist of team decepticons
     * @param numBattles Integer value for number of battles.
     */
    private void getResults(ArrayList<BeansPlayer> teamAutobot,ArrayList<BeansPlayer> teamDecepticon,int numBattles)
    {
        int winCountAuto=0; //Win count fro Autobots
        int winCountDec=0;//Win count for Decepticons
        int winnerPlayer;//Type of winner either PLAYER_TYPE_AUTO or PLAYER_TYPE_DEC

        //Travserse both list for number of battles. That way we'll not get index out of bound exception if both lists are not equal
        for (int i=0;i<numBattles;i++)
        {

            if((teamAutobot.get(i).getName().equals("Optimus Prime")||teamAutobot.get(i).getName().equals("Predaking"))
                    &&(teamDecepticon.get(i).getName().equals("Optimus Prime")||teamDecepticon.get(i).getName().equals("Predaking")))
            {
                System.out.println("Game ends as all competitors destroyed");//Condition if  Optimus Prime or Predaking face each other
                break;
            }
            else
            {
                if(teamAutobot.get(i).getName().equals("Optimus Prime")||teamAutobot.get(i).getName().equals("Predaking"))
                {
                    winnerPlayer=PLAYER_TYPE_AUTO;//Condition for named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria for Autobots
                }
                else if(teamDecepticon.get(i).getName().equals("Optimus Prime")||teamDecepticon.get(i).getName().equals("Predaking"))
                {
                    winnerPlayer=PLAYER_TYPE_DEC;//Condition for named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria for Decepticons
                }
                else
                {
                    if(teamAutobot.get(i).getSkill()-teamDecepticon.get(i).getSkill()>=3)
                    {
                        winnerPlayer=PLAYER_TYPE_AUTO;//Condition for Skill if Autobot wins
                    }
                    else if(teamDecepticon.get(i).getSkill()-teamAutobot.get(i).getSkill()>=3)
                    {
                        winnerPlayer=PLAYER_TYPE_DEC;//Condition for Skill if Decepticon wins
                    }
                    else if((teamAutobot.get(i).getCourage()-teamDecepticon.get(i).getCourage()>=4)
                            ||((teamAutobot.get(i).getStrength()-teamDecepticon.get(i).getStrength()>=3)))
                    {
                        winnerPlayer=PLAYER_TYPE_AUTO;//Condition for Courage and Strength if Autobot wins
                    }
                    else if((teamDecepticon.get(i).getCourage()-teamAutobot.get(i).getCourage()>=4)
                        ||((teamDecepticon.get(i).getStrength()-teamAutobot.get(i).getStrength()>=3)))
                    {
                        winnerPlayer=PLAYER_TYPE_DEC;//Condition for Courage and Strength if Decepticon wins
                    }
                    else
                    {
                        //Here we check based upon total strength
                        if(calculateStrength(teamAutobot.get(i))>calculateStrength(teamDecepticon.get(i)))
                        {
                            winnerPlayer=PLAYER_TYPE_AUTO;
                        }
                        else if(calculateStrength(teamDecepticon.get(i))>calculateStrength(teamAutobot.get(i)))
                        {
                            winnerPlayer=PLAYER_TYPE_DEC;
                        }
                        else
                        {
                            winnerPlayer=MATCH_TIE;
                        }
                    }

                }
            }

            if(winnerPlayer==PLAYER_TYPE_AUTO)
            {
                teamAutobot.get(i).setResult(TYPE_WIN);
                teamDecepticon.get(i).setResult(TYPE_LOSE);
                winCountAuto++;
            }
            else if(winnerPlayer==PLAYER_TYPE_DEC)
            {
                teamAutobot.get(i).setResult(TYPE_LOSE);
                teamDecepticon.get(i).setResult(TYPE_WIN);
                winCountDec++;
            }
            else
            {
                teamAutobot.get(i).setResult(TYPE_LOSE);
                teamDecepticon.get(i).setResult(TYPE_LOSE);
            }
        }
        if(winCountAuto>winCountDec)
        {
            System.out.println(numBattles+" battle");
            System.out.println("Winning Team(Autobots):"+printWinningTeam(teamAutobot,numBattles));
            System.out.println("Survivors from losing team(Decepticons):"+printSurvivorInfo(teamDecepticon));
        }
        else
        {
            System.out.println(numBattles+" battle");
            System.out.println("Winning Team(Decepticons):"+printWinningTeam(teamDecepticon,numBattles));
            System.out.println("Survivors from losing team(Autobots):"+printSurvivorInfo(teamAutobot));
        }

    }

    /**
     * Checks victors based upon result
     * @param teamInfo Arraylist to check for Victors
     * @param numOfBattle Integer value as we want to check only those player who fought and won
     * @return String name value of all winners in provided list
     */
    private String printWinningTeam(ArrayList<BeansPlayer> teamInfo,int numOfBattle)
    {
        String strWinningInfo="";
        for (int i=0;i<numOfBattle;i++)
        {
            if(teamInfo.get(i).getResult()==TYPE_WIN)
            {
                strWinningInfo=strWinningInfo+","+teamInfo.get(i).getName();
            }
        }

        return strWinningInfo;
    }

    /**
     * Checks survivors based upon result
     * @param teamInfo Arraylist to check for Survivors
     * @return String name value of all survivors in provided list
     */
    private String printSurvivorInfo(ArrayList<BeansPlayer> teamInfo)
    {
        String strSurvivorInfo="";
        for (int i=0;i<teamInfo.size();i++)
        {
            if((teamInfo.get(i).getResult()==TYPE_WIN))// Everyone who either won or skipped is survivor in losing team. We have def set to 1. This way all victors and skipped players will have 1
            {
                strSurvivorInfo=strSurvivorInfo+","+teamInfo.get(i).getName();
            }
        }

        return strSurvivorInfo;
    }

    /**
     * To calculate strength of player
     * @param playerInfo Player for which we want to calculate strength
     * @return Integer value for calculated strength
     */
    private int calculateStrength(BeansPlayer playerInfo)
    {
        return  playerInfo.getStrength()+playerInfo.getEndurance()+playerInfo.getIntelligence()+playerInfo.getSpeed()+ playerInfo.getFirepower();
    }
}