package com.zhenyu.zhenyu;

import services.ModuleLectureXML;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ModuleLectureXML module = new ModuleLectureXML();
        module.LectureLivraisonEntrepot("E:\\PLD AGILE\\TP\\zhenyu\\fichiersXML2018\\dl-petit-3.xml");
    }
}
