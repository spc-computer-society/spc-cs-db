
/*
 *     SPC-Comp-Soc
 *     Copyright (C) 2020  Colin Chow
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package spc.compsoc.example;

import java.util.ArrayList;
import java.util.List;

public class MakeName {
    private static List<String> firstChiName = new ArrayList<>();
    private static List<String> remainChi = new ArrayList<>();
    private static List<String> firstEngName = new ArrayList<>();
    private static List<String> lastEngName = new ArrayList<>();
    private MakeName(){
        throw new AssertionError();
    }

    /**
     * Generates a name according to the install list of names.
     * @param lang The language for which this name should be
     * @return THe generated name
     */
    public static String getName(Language lang){
        if(lang.equals(Language.CHI)) {
            if(RandomUtil.getNextBoundedInt(20) == 1){
                String second = remainChi.get(RandomUtil.getNextBoundedInt(remainChi.size()));
                return firstChiName.get(RandomUtil.getNextBoundedInt(firstChiName.size())).concat(second);
            }else {
                String second = remainChi.get(RandomUtil.getNextBoundedInt(remainChi.size()));
                String third = remainChi.get(RandomUtil.getNextBoundedInt(remainChi.size()));
                if (second.equals(third)) {
                    third = remainChi.get(RandomUtil.getNextBoundedInt(remainChi.size()));
                }
                if(second.length() != 1){
                    if(!(RandomUtil.getNextBoundedInt(5) == 1)){
                        second = remainChi.get(RandomUtil.getNextBoundedInt(remainChi.size()));
                    }
                }
                if((second.length() != 1) || (third.length() != 1)){
                    if(RandomUtil.getNextBoundedInt(1) == 0) {
                        return firstChiName.get(RandomUtil.getNextBoundedInt(firstChiName.size())).concat(second);
                    }else{
                        return firstChiName.get(RandomUtil.getNextBoundedInt(firstChiName.size())).concat(third);
                    }
                }
                return firstChiName.get(RandomUtil.getNextBoundedInt(firstChiName.size())).concat(second).concat(third);
            }
        }else{
            String second = lastEngName.get(RandomUtil.getNextBoundedInt(lastEngName.size()));
            return firstEngName.get(RandomUtil.getNextBoundedInt(firstEngName.size())).concat(second);
        }
    }

    /**
     * Replaces the list of first names in the given language.
     * @param lang The language database to replace
     * @param toReplace The supplied list of first names
     */
    public static void replaceFirstDB(Language lang,List<String> toReplace){
        if(Language.ENG.equals(lang)){
            firstEngName = toReplace;
        }else{
            firstChiName = toReplace;
        }
    }

    /**
     * Replaces the list of last names of the given language.
     * @param lang The language to replace the list for
     * @param toReplace The supplied list of last names
     */
    public static void replaceLaterDB(Language lang,List<String> toReplace){
        if(Language.ENG.equals(lang)){
            lastEngName = toReplace;
        }else{
            remainChi = toReplace;
        }
    }
    public enum Language{
        ENG,CHI
    }
}
