/**
* Essential OCL Lexer
* <copyright>
*
* Copyright (c) 2005, 2009 IBM Corporation and others.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   IBM - Initial API and implementation
*   E.D.Willink - Lexer and Parser refactoring to support extensibility and flexible error handling
*   Borland - Bug 242880
*   E.D.Willink - Bug 292112
* </copyright>
*
* $Id: QvtOpLexerprs.java,v 1.79.4.2 2009/10/29 07:17:23 sboyko Exp $
*/
/**
* Complete OCL Lexer
* <copyright>
*
* Copyright (c) 2005, 2009 IBM Corporation and others.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   IBM - Initial API and implementation
*   E.D.Willink - Bug 292112, 292594
* </copyright>
*/
/**
* <copyright>
*
* Copyright (c) 2006-2008 Borland Inc.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Borland - Initial API and implementation
*
* </copyright>
*
* $Id: QvtOpLexerprs.java,v 1.79.4.2 2009/10/29 07:17:23 sboyko Exp $
*/

package org.eclipse.m2m.internal.qvt.oml.cst.parser;

public class QvtOpLexerprs implements lpg.lpgjavaruntime.ParseTable, QvtOpLexersym {

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            1,1,3,3,3,1,2,2,1,1,
            5,1,1,1,1,1,1,1,1,1,
            1,2,2,2,1,1,1,1,2,1,
            1,1,2,1,1,1,1,2,1,1,
            2,2,3,2,2,0,1,2,2,2,
            1,2,3,2,3,3,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            2,1,2,2,2,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            2,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,2,1,2,1,
            2,0,1,1,1,2,1,2,2,1,
            2,2,2,3,1,3,1,1,1,1,
            1,1,1,3,0,1,2
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            21,21,21,21,21,21,21,21,21,21,
            21,21,21,21,21,21,21,21,21,21,
            21,21,21,21,21,21,21,21,21,21,
            21,21,21,21,21,21,13,21,14,24,
            25,25,25,27,27,27,27,28,28,26,
            26,7,7,30,15,15,15,10,10,10,
            10,10,2,2,2,2,3,3,3,3,
            3,3,3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,3,3,
            3,3,4,4,4,4,4,4,4,4,
            4,4,4,4,4,4,4,4,4,4,
            4,4,4,4,4,4,4,4,1,1,
            1,1,1,1,1,1,1,1,17,17,
            29,29,22,22,22,22,32,32,32,32,
            32,32,32,32,32,32,32,32,32,32,
            32,32,32,32,32,32,32,32,32,32,
            32,32,32,32,32,32,18,18,18,18,
            18,18,18,18,18,18,18,18,18,18,
            18,18,18,18,18,18,18,18,18,18,
            18,18,18,18,18,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,19,19,8,8,33,33,33,
            33,6,16,16,16,16,31,31,31,31,
            31,31,31,31,34,34,34,34,20,20,
            20,20,9,9,9,9,9,23,35,35,
            11,11,12,12,21,21,21,21,21,21,
            21,21,21,21,21,21,21,9,9,9,
            20,20,20,20,21,36,36,26,905,451,
            446,446,446,1194,296,425,1244,52,460,1205,
            296,35,37,1107,134,133,133,133,1308,299,
            303,2,435,9,405,1255,52,353,397,507,
            462,462,462,462,462,462,307,390,390,390,
            390,390,390,1306,390,390,1303,390,462,462,
            607,466,466,466,466,466,466,1301,466,466,
            1302,376,434,1266,369,415,607,466,466,466,
            466,466,466,131,466,466,1311,376,438,706,
            259,259,259,259,259,259,806,261,261,261,
            261,261,261,1318,261,261,296,1304,259,259,
            407,261,261,261,261,261,261,988,261,261,
            1,45,45,45,45,203,50,50,50,50,
            45,1305,41,400,293,1100,45,1323,45,1007,
            231,231,231,231,231,1179,52,1324,429,102,
            43,43,43,43,1000,1315,50,50,1208,43,
            42,1325,293,1211,315,43,1310,43,7,8,
            315,1326,231,231,1222,458,1233,464,1268,52,
            1328,458,1329,464,1279,52,1331,469,469
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,88,89,
            90,91,92,93,94,95,96,97,98,99,
            100,0,1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,34,35,36,37,38,
            39,40,41,42,43,44,45,46,47,48,
            49,50,51,52,53,54,55,56,57,58,
            59,60,61,62,63,64,65,66,67,68,
            69,70,71,72,73,74,75,76,77,78,
            79,80,81,82,83,84,85,86,87,88,
            89,90,91,92,93,94,95,96,97,98,
            99,100,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,36,37,
            38,39,40,41,42,43,44,45,46,47,
            48,49,50,51,52,53,54,55,56,57,
            58,59,60,61,62,63,64,65,66,67,
            68,69,70,71,72,73,74,75,76,77,
            78,79,80,81,82,83,84,85,86,87,
            88,89,90,91,92,0,0,95,96,97,
            98,99,100,0,0,103,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,78,79,80,81,82,83,
            84,85,86,87,88,89,90,91,92,93,
            94,95,96,97,98,99,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,78,79,80,81,82,83,
            84,85,86,87,88,89,90,91,92,93,
            94,95,96,97,98,99,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,0,78,79,80,81,82,83,
            84,85,86,87,88,89,90,91,92,93,
            94,95,96,97,98,99,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,78,79,80,81,82,83,
            84,85,86,87,88,89,90,91,92,93,
            94,95,96,97,98,0,1,2,3,4,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,34,
            35,36,37,38,39,40,41,42,43,44,
            45,46,47,48,49,50,51,52,53,54,
            55,56,57,58,59,60,61,62,63,64,
            65,66,67,68,69,70,71,72,73,74,
            75,76,0,78,79,80,81,82,83,84,
            85,86,87,88,89,90,91,92,93,94,
            95,96,97,98,99,0,1,2,3,4,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,34,
            35,36,37,38,39,40,41,42,43,44,
            45,46,47,48,49,50,51,52,53,54,
            55,56,57,58,59,60,61,62,63,64,
            65,66,67,68,69,70,71,72,73,74,
            75,76,77,78,79,80,81,82,83,84,
            85,86,87,88,89,90,91,92,93,94,
            95,96,97,98,0,1,2,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,39,40,41,42,43,44,45,
            46,47,48,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,65,
            66,67,68,69,70,71,72,73,74,75,
            76,77,78,79,80,81,82,0,84,85,
            86,87,88,89,90,91,92,93,94,0,
            13,14,0,99,100,101,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,0,77,78,79,80,81,82,83,
            84,85,86,87,88,89,90,91,92,0,
            101,95,96,97,98,99,0,1,2,3,
            4,5,6,7,8,9,10,0,0,13,
            14,0,0,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,0,77,0,0,1,
            2,3,4,5,6,7,8,9,10,83,
            12,13,14,0,1,2,3,4,5,6,
            7,8,9,10,0,0,0,0,0,16,
            0,1,2,3,4,5,6,7,8,9,
            10,0,1,2,3,4,5,6,7,8,
            9,10,0,1,2,3,4,5,6,7,
            8,9,10,0,1,2,3,4,5,6,
            7,8,9,10,0,1,2,3,4,5,
            6,7,8,9,10,0,73,0,1,2,
            3,4,5,6,7,8,9,10,0,1,
            2,3,4,5,6,7,8,9,10,0,
            1,2,3,4,5,6,7,8,9,10,
            0,0,0,0,0,0,102,0,101,0,
            0,11,11,11,0,15,15,0,11,15,
            16,11,0,0,0,0,12,0,0,12,
            0,0,0,11,69,12,12,0,0,0,
            0,76,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,93,94,
            0,0,0,0,0,100,0,0,0,0,
            0,0,72,71,0,70,0,74,75,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,77,0,0,0,0,
            0,102,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,102,0,102,
            0,101,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            469,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,513,429,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,469,512,512,512,512,512,512,512,512,
            512,512,512,512,512,512,512,512,512,512,
            512,512,512,512,512,512,512,512,512,512,
            512,512,512,512,512,512,512,512,512,512,
            512,512,512,512,512,512,512,512,512,512,
            512,512,512,512,512,512,512,512,512,512,
            512,512,512,512,512,512,512,512,512,512,
            512,512,512,512,512,480,517,512,512,512,
            512,512,512,512,512,512,512,512,512,512,
            512,512,512,512,512,512,512,512,512,512,
            512,512,10,519,519,519,519,519,519,519,
            519,519,519,519,519,519,519,519,519,519,
            519,519,519,519,519,519,519,519,519,519,
            519,519,519,519,519,519,519,519,519,519,
            519,519,519,519,519,519,519,519,519,519,
            519,519,519,519,519,519,519,519,519,519,
            519,519,519,519,519,519,519,519,519,519,
            519,519,519,519,519,519,519,519,519,519,
            519,519,519,519,519,519,519,519,519,519,
            519,519,519,519,519,46,469,519,519,519,
            519,519,519,469,469,519,469,390,390,390,
            390,390,390,390,390,390,390,390,390,390,
            390,390,390,390,390,390,390,390,390,390,
            390,390,390,390,390,390,390,390,390,390,
            390,390,390,390,390,390,390,390,390,390,
            390,390,390,390,390,390,390,390,390,390,
            390,390,390,390,390,390,390,390,390,390,
            390,390,390,390,390,390,390,390,390,390,
            390,390,390,390,390,390,390,390,390,390,
            390,390,390,390,390,390,390,390,390,390,
            390,390,390,390,419,726,469,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,419,472,285,462,462,462,
            462,462,462,462,462,462,462,462,462,462,
            462,462,462,462,462,462,462,462,462,462,
            462,462,462,462,462,462,462,462,462,462,
            462,462,462,462,462,462,462,462,462,462,
            462,462,462,462,462,462,462,462,462,462,
            462,462,462,462,462,462,462,462,462,462,
            462,462,462,462,462,462,462,462,462,462,
            462,462,462,469,462,462,462,462,462,462,
            462,462,462,462,462,462,462,462,462,462,
            462,462,462,462,419,462,262,466,466,466,
            466,466,466,466,466,466,466,466,466,466,
            466,466,466,466,466,466,466,466,466,466,
            466,466,466,466,466,466,466,466,466,466,
            466,466,466,466,466,466,466,466,466,466,
            466,466,466,466,466,466,466,466,466,466,
            466,466,466,466,466,466,466,466,466,466,
            466,466,466,466,466,466,466,466,466,466,
            466,466,466,466,466,466,466,466,466,466,
            466,466,466,466,466,466,466,466,466,466,
            466,466,466,466,419,286,728,728,728,728,
            728,728,728,728,728,728,728,728,728,728,
            728,728,728,728,728,728,728,728,728,728,
            728,728,728,728,728,728,728,728,728,728,
            728,728,728,728,728,728,728,728,728,728,
            728,728,728,728,728,728,728,728,728,728,
            728,728,728,728,728,728,728,728,728,728,
            728,728,728,728,728,728,728,728,728,728,
            728,728,469,728,728,728,728,728,728,728,
            728,728,728,728,728,728,728,728,728,728,
            728,728,728,419,728,263,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,730,730,730,730,730,730,730,
            730,730,730,419,469,451,451,451,451,451,
            451,451,451,451,451,490,383,446,446,350,
            411,446,446,446,446,446,446,446,446,446,
            446,446,446,446,446,446,446,446,446,446,
            446,446,446,446,446,446,446,446,446,446,
            446,446,446,446,446,446,446,446,446,446,
            446,446,446,446,446,446,446,446,446,446,
            446,446,446,460,333,336,347,366,387,484,
            460,319,499,308,733,340,744,9,503,496,
            497,494,495,736,500,486,487,460,460,469,
            293,293,469,326,460,356,469,700,700,700,
            700,700,700,700,700,700,700,700,700,700,
            700,700,700,700,700,700,700,700,700,700,
            700,700,700,700,700,700,700,700,700,700,
            700,700,700,700,700,700,700,700,700,700,
            700,700,700,700,700,700,700,700,700,700,
            700,700,700,700,700,700,700,700,700,700,
            700,700,700,700,700,700,700,700,700,700,
            700,700,469,700,700,700,700,700,700,700,
            700,700,700,700,700,700,700,700,700,469,
            474,700,700,700,700,700,1,603,603,603,
            603,603,603,603,603,603,603,469,469,602,
            602,469,469,602,602,602,602,602,602,602,
            602,602,602,602,602,602,602,602,602,602,
            602,602,602,602,602,602,602,602,602,602,
            602,602,602,602,602,602,602,602,602,602,
            602,602,602,602,602,602,602,602,602,602,
            602,602,602,602,602,469,753,469,39,521,
            521,521,521,521,521,521,521,521,521,604,
            2046,293,293,469,296,296,296,296,296,296,
            296,296,296,296,469,469,469,469,469,456,
            469,315,315,315,315,315,315,315,315,315,
            315,469,458,458,458,458,458,458,458,458,
            458,458,469,464,464,464,464,464,464,464,
            464,464,464,54,521,521,521,521,521,521,
            521,521,521,521,53,521,521,521,521,521,
            521,521,521,521,521,12,454,56,521,521,
            521,521,521,521,521,521,521,521,55,521,
            521,521,521,521,521,521,521,521,521,39,
            443,443,443,443,443,443,443,443,443,443,
            20,19,32,16,14,265,468,270,473,132,
            13,492,491,737,6,493,742,36,740,498,
            518,738,33,36,38,51,427,130,258,441,
            260,469,469,745,600,507,743,469,469,469,
            469,600,469,469,469,469,469,469,469,469,
            469,469,469,469,469,469,469,469,600,600,
            469,469,469,469,469,600,469,469,469,469,
            469,469,741,417,469,735,469,756,386,469,
            469,469,469,469,469,469,469,469,469,469,
            469,469,469,469,469,469,469,469,469,469,
            469,469,469,469,469,286,469,469,469,469,
            469,1,469,469,469,469,469,469,469,469,
            469,469,469,469,469,469,469,39,469,12,
            469,263
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int getErrorSymbol() { return 0; }
    public final int getScopeUbound() { return 0; }
    public final int getScopeSize() { return 0; }
    public final int getMaxNameLength() { return 0; }

    public final static int
           NUM_STATES        = 47,
           NT_OFFSET         = 103,
           LA_STATE_OFFSET   = 756,
           MAX_LA            = 2,
           NUM_RULES         = 287,
           NUM_NONTERMINALS  = 37,
           NUM_SYMBOLS       = 140,
           SEGMENT_SIZE      = 8192,
           START_STATE       = 288,
           IDENTIFIER_SYMBOL = 0,
           EOFT_SYMBOL       = 102,
           EOLT_SYMBOL       = 104,
           ACCEPT_ACTION     = 468,
           ERROR_ACTION      = 469;

    public final static boolean BACKTRACK = false;

    public final int getNumStates() { return NUM_STATES; }
    public final int getNtOffset() { return NT_OFFSET; }
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }
    public final int getMaxLa() { return MAX_LA; }
    public final int getNumRules() { return NUM_RULES; }
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }
    public final int getNumSymbols() { return NUM_SYMBOLS; }
    public final int getSegmentSize() { return SEGMENT_SIZE; }
    public final int getStartState() { return START_STATE; }
    public final int getStartSymbol() { return lhs[0]; }
    public final int getIdentifierSymbol() { return IDENTIFIER_SYMBOL; }
    public final int getEoftSymbol() { return EOFT_SYMBOL; }
    public final int getEoltSymbol() { return EOLT_SYMBOL; }
    public final int getAcceptAction() { return ACCEPT_ACTION; }
    public final int getErrorAction() { return ERROR_ACTION; }
    public final boolean isValidForParser() { return isValidForParser; }
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}
