package com.example.navde.myapplication;

/**
 * @author jaspreet
 * This class contains logic to calculate number of castles that should be built on a stretch of land
 */

public class CastleCompany {

    /**
     * Calulate number of castle that should be build based on provided array
     * @param listLandInfo Array of Integer type
     * @return Integer Number of castles that can be built
     */
    private int getCastleInfo(Integer[] listLandInfo)
    {
        int numOfCastle=0;//Initial val for number of castles
        if((listLandInfo!=null)&&(listLandInfo.length>0))
        {
            int pvStartCheck=listLandInfo[0];
            numOfCastle++;// As we can allways make castle on first position
            for (int i=1;i<listLandInfo.length-1;i++)
            {
                if(pvStartCheck==listLandInfo[i])
                {
                    continue;//If next item is same them skip it
                }
                else
                {
                    if((listLandInfo[i]<pvStartCheck)&&(listLandInfo[i]<listLandInfo[i+1])
                            ||(listLandInfo[i]>pvStartCheck)&&(listLandInfo[i]>listLandInfo[i+1]))
                    {
                        numOfCastle++;//Incrementing number of castle
                        pvStartCheck=listLandInfo[i];// Keeps check of start value to make castle
                    }
                }

            }
        }
        return numOfCastle;
    }

}
