/**
* <copyright>
*
* Copyright (c) 2005, 2008 IBM Corporation and others.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   IBM - Initial API and implementation
*   E.D.Willink - Elimination of some shift-reduce conflicts
*   E.D.Willink - Remove unnecessary warning suppression
*   E.D.Willink - 225493 Need ability to set CSTNode offsets
*
* </copyright>
*
* $Id: QvtOpLPGParsersym.java,v 1.9 2008/10/08 19:41:59 aigdalov Exp $
*/
/**
* <copyright>
*
* Copyright (c) 2006, 2007 Borland Inc.
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
* $Id: QvtOpLPGParsersym.java,v 1.9 2008/10/08 19:41:59 aigdalov Exp $
*/

package org.eclipse.m2m.internal.qvt.oml.cst.parser;

public interface QvtOpLPGParsersym {
    public final static int
      TK_NUMERIC_OPERATION = 67,
      TK_STRING_LITERAL = 46,
      TK_INTEGER_LITERAL = 68,
      TK_REAL_LITERAL = 69,
      TK_PLUS = 51,
      TK_MINUS = 52,
      TK_MULTIPLY = 27,
      TK_DIVIDE = 28,
      TK_GREATER = 21,
      TK_LESS = 22,
      TK_EQUAL = 19,
      TK_GREATER_EQUAL = 23,
      TK_LESS_EQUAL = 24,
      TK_NOT_EQUAL = 25,
      TK_LPAREN = 1,
      TK_RPAREN = 4,
      TK_LBRACE = 86,
      TK_RBRACE = 85,
      TK_LBRACKET = 108,
      TK_RBRACKET = 121,
      TK_ARROW = 137,
      TK_BAR = 90,
      TK_COMMA = 87,
      TK_COLON = 88,
      TK_COLONCOLON = 89,
      TK_SEMICOLON = 84,
      TK_DOT = 109,
      TK_DOTDOT = 138,
      TK_ATPRE = 122,
      TK_CARET = 139,
      TK_CARETCARET = 140,
      TK_QUESTIONMARK = 123,
      TK_QUOTE_STRING_LITERAL = 131,
      TK_ADD_ASSIGN = 132,
      TK_RESET_ASSIGN = 102,
      TK_AT_SIGN = 124,
      TK_EXCLAMATION_MARK = 125,
      TK_NOT_EQUAL_EXEQ = 113,
      TK_self = 29,
      TK_inv = 157,
      TK_pre = 158,
      TK_post = 159,
      TK_context = 160,
      TK_package = 161,
      TK_endpackage = 162,
      TK_def = 163,
      TK_if = 70,
      TK_then = 141,
      TK_else = 133,
      TK_endif = 134,
      TK_and = 47,
      TK_or = 48,
      TK_xor = 49,
      TK_not = 54,
      TK_implies = 142,
      TK_let = 75,
      TK_in = 92,
      TK_true = 71,
      TK_false = 72,
      TK_body = 30,
      TK_derive = 31,
      TK_init = 26,
      TK_null = 53,
      TK_attr = 164,
      TK_oper = 165,
      TK_Set = 5,
      TK_Bag = 6,
      TK_Sequence = 7,
      TK_Collection = 8,
      TK_OrderedSet = 9,
      TK_iterate = 32,
      TK_forAll = 33,
      TK_exists = 34,
      TK_isUnique = 35,
      TK_any = 36,
      TK_one = 37,
      TK_collect = 38,
      TK_select = 39,
      TK_reject = 40,
      TK_collectNested = 41,
      TK_sortedBy = 42,
      TK_closure = 43,
      TK_oclIsKindOf = 55,
      TK_oclIsTypeOf = 56,
      TK_oclAsType = 57,
      TK_oclIsNew = 58,
      TK_oclIsUndefined = 59,
      TK_oclIsInvalid = 60,
      TK_oclIsInState = 61,
      TK_allInstances = 44,
      TK_String = 10,
      TK_Integer = 11,
      TK_UnlimitedNatural = 12,
      TK_Real = 13,
      TK_Boolean = 14,
      TK_Tuple = 20,
      TK_OclAny = 15,
      TK_OclVoid = 16,
      TK_Invalid = 17,
      TK_OclMessage = 18,
      TK_OclInvalid = 73,
      TK_end = 126,
      TK_while = 74,
      TK_out = 103,
      TK_object = 64,
      TK_transformation = 127,
      TK_import = 128,
      TK_library = 114,
      TK_metamodel = 115,
      TK_mapping = 104,
      TK_query = 105,
      TK_helper = 106,
      TK_inout = 107,
      TK_when = 116,
      TK_var = 94,
      TK_configuration = 110,
      TK_intermediate = 111,
      TK_property = 101,
      TK_population = 144,
      TK_map = 62,
      TK_xmap = 63,
      TK_late = 65,
      TK_log = 93,
      TK_assert = 95,
      TK_with = 145,
      TK_resolve = 76,
      TK_resolveone = 77,
      TK_resolveIn = 78,
      TK_resolveoneIn = 79,
      TK_invresolve = 80,
      TK_invresolveone = 81,
      TK_invresolveIn = 82,
      TK_invresolveoneIn = 83,
      TK_modeltype = 117,
      TK_uses = 146,
      TK_where = 147,
      TK_refines = 135,
      TK_enforcing = 148,
      TK_access = 129,
      TK_extends = 130,
      TK_blackbox = 96,
      TK_abstract = 97,
      TK_static = 98,
      TK_result = 45,
      TK_main = 91,
      TK_this = 50,
      TK_switch = 66,
      TK_case = 143,
      TK_xselect = 149,
      TK_xcollect = 150,
      TK_selectOne = 151,
      TK_collectOne = 152,
      TK_collectselect = 153,
      TK_collectselectOne = 154,
      TK_return = 99,
      TK_rename = 112,
      TK_disjuncts = 118,
      TK_merges = 119,
      TK_inherits = 120,
      TK_forEach = 155,
      TK_forOne = 156,
      TK_EOF_TOKEN = 100,
      TK_IDENTIFIER = 3,
      TK_INTEGER_RANGE_START = 136,
      TK_ERROR_TOKEN = 2;

      public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "ERROR_TOKEN",
                 "IDENTIFIER",
                 "RPAREN",
                 "Set",
                 "Bag",
                 "Sequence",
                 "Collection",
                 "OrderedSet",
                 "String",
                 "Integer",
                 "UnlimitedNatural",
                 "Real",
                 "Boolean",
                 "OclAny",
                 "OclVoid",
                 "Invalid",
                 "OclMessage",
                 "EQUAL",
                 "Tuple",
                 "GREATER",
                 "LESS",
                 "GREATER_EQUAL",
                 "LESS_EQUAL",
                 "NOT_EQUAL",
                 "init",
                 "MULTIPLY",
                 "DIVIDE",
                 "self",
                 "body",
                 "derive",
                 "iterate",
                 "forAll",
                 "exists",
                 "isUnique",
                 "any",
                 "one",
                 "collect",
                 "select",
                 "reject",
                 "collectNested",
                 "sortedBy",
                 "closure",
                 "allInstances",
                 "result",
                 "STRING_LITERAL",
                 "and",
                 "or",
                 "xor",
                 "this",
                 "PLUS",
                 "MINUS",
                 "null",
                 "not",
                 "oclIsKindOf",
                 "oclIsTypeOf",
                 "oclAsType",
                 "oclIsNew",
                 "oclIsUndefined",
                 "oclIsInvalid",
                 "oclIsInState",
                 "map",
                 "xmap",
                 "object",
                 "late",
                 "switch",
                 "NUMERIC_OPERATION",
                 "INTEGER_LITERAL",
                 "REAL_LITERAL",
                 "if",
                 "true",
                 "false",
                 "OclInvalid",
                 "while",
                 "let",
                 "resolve",
                 "resolveone",
                 "resolveIn",
                 "resolveoneIn",
                 "invresolve",
                 "invresolveone",
                 "invresolveIn",
                 "invresolveoneIn",
                 "SEMICOLON",
                 "RBRACE",
                 "LBRACE",
                 "COMMA",
                 "COLON",
                 "COLONCOLON",
                 "BAR",
                 "main",
                 "in",
                 "log",
                 "var",
                 "assert",
                 "blackbox",
                 "abstract",
                 "static",
                 "return",
                 "EOF_TOKEN",
                 "property",
                 "RESET_ASSIGN",
                 "out",
                 "mapping",
                 "query",
                 "helper",
                 "inout",
                 "LBRACKET",
                 "DOT",
                 "configuration",
                 "intermediate",
                 "rename",
                 "NOT_EQUAL_EXEQ",
                 "library",
                 "metamodel",
                 "when",
                 "modeltype",
                 "disjuncts",
                 "merges",
                 "inherits",
                 "RBRACKET",
                 "ATPRE",
                 "QUESTIONMARK",
                 "AT_SIGN",
                 "EXCLAMATION_MARK",
                 "end",
                 "transformation",
                 "import",
                 "access",
                 "extends",
                 "QUOTE_STRING_LITERAL",
                 "ADD_ASSIGN",
                 "else",
                 "endif",
                 "refines",
                 "INTEGER_RANGE_START",
                 "ARROW",
                 "DOTDOT",
                 "CARET",
                 "CARETCARET",
                 "then",
                 "implies",
                 "case",
                 "population",
                 "with",
                 "uses",
                 "where",
                 "enforcing",
                 "xselect",
                 "xcollect",
                 "selectOne",
                 "collectOne",
                 "collectselect",
                 "collectselectOne",
                 "forEach",
                 "forOne",
                 "inv",
                 "pre",
                 "post",
                 "context",
                 "package",
                 "endpackage",
                 "def",
                 "attr",
                 "oper"
             };

    public final static boolean isValidForParser = true;
}
