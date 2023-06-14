package Fullproject.Melody;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

class TileFactory 
{
    public static List<int[]> getTileMappings(int trackNum) {
        List<int[]> tileMappings = new ArrayList<>();


        /*      tileMappings.add(new int[]{0, 0});	 A
                tileMappings.add(new int[]{100, 0}); W
                tileMappings.add(new int[]{200, 0}); S
                tileMappings.add(new int[]{300, 0}); E
                tileMappings.add(new int[]{400, 0}); D
                tileMappings.add(new int[]{500, 0}); F
                tileMappings.add(new int[]{600, 0}); T
                tileMappings.add(new int[]{700, 0}); G
                tileMappings.add(new int[]{800, 0}); Y
                tileMappings.add(new int[]{900, 0}); H
                tileMappings.add(new int[]{1000, 0}); U
                tileMappings.add(new int[]{1100, 0}); J
        */
        int startTime = 5; // 시작 시간
        int gap = 30; // 노트 간격
        if (trackNum == 0) {
            // A 곡 타일 매핑
            tileMappings.add(new int[]{0, -(startTime + gap)});
            tileMappings.add(new int[]{100,-(startTime + gap*10)});
            tileMappings.add(new int[]{200, -(startTime + gap*20)});
            tileMappings.add(new int[]{300, -(startTime + gap*30)});
            tileMappings.add(new int[]{400, -(startTime + gap*50)});
        } else if (trackNum == 1) {
            // B 곡 타일 매핑
            tileMappings.add(new int[]{100,-(startTime + gap*10)});
        } else if (trackNum == 2) {
            // C 곡 타일 매핑
            tileMappings.add(new int[]{100,-(startTime + gap*10)});
            tileMappings.add(new int[]{200, -(startTime + gap*20)});
            tileMappings.add(new int[]{300, -(startTime + gap*30)});
            tileMappings.add(new int[]{400, -(startTime + gap*50)});
        }

        return tileMappings;
    }
}
