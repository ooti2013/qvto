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
* $Id: LightweightParsersym.java,v 1.25 2008/12/02 14:47:27 aigdalov Exp $
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
* $Id: LightweightParsersym.java,v 1.25 2008/12/02 14:47:27 aigdalov Exp $
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
* $Id: LightweightParsersym.java,v 1.25 2008/12/02 14:47:27 aigdalov Exp $
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
* $Id: LightweightParsersym.java,v 1.25 2008/12/02 14:47:27 aigdalov Exp $
*/

package org.eclipse.m2m.internal.qvt.oml.editor.ui.completion.cst.parser;

public interface LightweightParsersym {
    public final static int
      TK_NUMERIC_OPERATION = 66,
      TK_STRING_LITERAL = 49,
      TK_INTEGER_LITERAL = 67,
      TK_REAL_LITERAL = 68,
      TK_PLUS = 50,
      TK_MINUS = 51,
      TK_MULTIPLY = 42,
      TK_DIVIDE = 43,
      TK_GREATER = 37,
      TK_LESS = 38,
      TK_EQUAL = 5,
      TK_GREATER_EQUAL = 39,
      TK_LESS_EQUAL = 40,
      TK_NOT_EQUAL = 41,
      TK_LPAREN = 1,
      TK_RPAREN = 3,
      TK_LBRACE = 87,
      TK_RBRACE = 88,
      TK_LBRACKET = 99,
      TK_RBRACKET = 110,
      TK_ARROW = 115,
      TK_BAR = 92,
      TK_COMMA = 89,
      TK_COLON = 90,
      TK_COLONCOLON = 91,
      TK_SEMICOLON = 86,
      TK_DOT = 100,
      TK_DOTDOT = 132,
      TK_ATPRE = 106,
      TK_CARET = 116,
      TK_CARETCARET = 117,
      TK_QUESTIONMARK = 111,
      TK_QUOTE_STRING_LITERAL = 118,
      TK_ADD_ASSIGN = 119,
      TK_RESET_ASSIGN = 98,
      TK_AT_SIGN = 112,
      TK_EXCLAMATION_MARK = 107,
      TK_NOT_EQUAL_EXEQ = 104,
      TK_INTEGER_RANGE_START = 120,
      TK_Dict = 160,
      TK_List = 161,
      TK_break = 162,
      TK_class = 143,
      TK_composes = 163,
      TK_constructor = 164,
      TK_continue = 165,
      TK_datatype = 166,
      TK_default = 167,
      TK_derived = 168,
      TK_do = 169,
      TK_elif = 170,
      TK_enum = 171,
      TK_except = 172,
      TK_exception = 173,
      TK_from = 174,
      TK_literal = 175,
      TK_ordered = 176,
      TK_primitive = 177,
      TK_raise = 178,
      TK_readonly = 179,
      TK_references = 180,
      TK_tag = 181,
      TK_try = 182,
      TK_typedef = 183,
      TK_unlimited = 184,
      TK_invalid = 185,
      TK_self = 44,
      TK_inv = 186,
      TK_pre = 187,
      TK_post = 188,
      TK_endpackage = 189,
      TK_def = 190,
      TK_if = 69,
      TK_then = 133,
      TK_else = 121,
      TK_endif = 122,
      TK_and = 45,
      TK_or = 46,
      TK_xor = 47,
      TK_not = 54,
      TK_implies = 123,
      TK_let = 77,
      TK_in = 94,
      TK_true = 70,
      TK_false = 71,
      TK_body = 21,
      TK_derive = 22,
      TK_init = 23,
      TK_null = 53,
      TK_Set = 6,
      TK_Bag = 7,
      TK_Sequence = 8,
      TK_Collection = 9,
      TK_OrderedSet = 10,
      TK_iterate = 24,
      TK_forAll = 25,
      TK_exists = 26,
      TK_isUnique = 27,
      TK_any = 28,
      TK_one = 29,
      TK_collect = 30,
      TK_select = 31,
      TK_reject = 32,
      TK_collectNested = 33,
      TK_sortedBy = 34,
      TK_closure = 35,
      TK_oclIsKindOf = 55,
      TK_oclIsTypeOf = 56,
      TK_oclAsType = 57,
      TK_oclIsNew = 58,
      TK_oclIsUndefined = 59,
      TK_oclIsInvalid = 60,
      TK_oclIsInState = 61,
      TK_allInstances = 36,
      TK_String = 11,
      TK_Integer = 12,
      TK_UnlimitedNatural = 13,
      TK_Real = 14,
      TK_Boolean = 15,
      TK_Tuple = 20,
      TK_OclAny = 16,
      TK_OclVoid = 17,
      TK_Invalid = 18,
      TK_OclMessage = 19,
      TK_OclInvalid = 72,
      TK_end = 101,
      TK_while = 73,
      TK_when = 113,
      TK_var = 95,
      TK_log = 93,
      TK_assert = 96,
      TK_with = 144,
      TK_switch = 64,
      TK_case = 134,
      TK_xselect = 145,
      TK_xcollect = 146,
      TK_selectOne = 147,
      TK_collectOne = 148,
      TK_collectselect = 149,
      TK_collectselectOne = 150,
      TK_return = 97,
      TK_forEach = 151,
      TK_forOne = 152,
      TK_compute = 74,
      TK_new = 75,
      TK_out = 102,
      TK_object = 76,
      TK_transformation = 135,
      TK_import = 153,
      TK_library = 124,
      TK_metamodel = 154,
      TK_mapping = 136,
      TK_query = 137,
      TK_helper = 138,
      TK_inout = 103,
      TK_configuration = 125,
      TK_intermediate = 126,
      TK_property = 108,
      TK_population = 139,
      TK_map = 62,
      TK_xmap = 63,
      TK_late = 65,
      TK_resolve = 78,
      TK_resolveone = 79,
      TK_resolveIn = 80,
      TK_resolveoneIn = 81,
      TK_invresolve = 82,
      TK_invresolveone = 83,
      TK_invresolveIn = 84,
      TK_invresolveoneIn = 85,
      TK_modeltype = 155,
      TK_uses = 156,
      TK_where = 157,
      TK_refines = 127,
      TK_enforcing = 158,
      TK_access = 114,
      TK_extends = 109,
      TK_blackbox = 140,
      TK_abstract = 141,
      TK_static = 142,
      TK_result = 48,
      TK_main = 105,
      TK_this = 52,
      TK_rename = 159,
      TK_inherits = 128,
      TK_merges = 129,
      TK_disjuncts = 130,
      TK_IDENTIFIER = 2,
      TK_ERROR_TOKEN = 4,
      TK_EOF_TOKEN = 131;

      public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "IDENTIFIER",
                 "RPAREN",
                 "ERROR_TOKEN",
                 "EQUAL",
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
                 "Tuple",
                 "body",
                 "derive",
                 "init",
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
                 "GREATER",
                 "LESS",
                 "GREATER_EQUAL",
                 "LESS_EQUAL",
                 "NOT_EQUAL",
                 "MULTIPLY",
                 "DIVIDE",
                 "self",
                 "and",
                 "or",
                 "xor",
                 "result",
                 "STRING_LITERAL",
                 "PLUS",
                 "MINUS",
                 "this",
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
                 "switch",
                 "late",
                 "NUMERIC_OPERATION",
                 "INTEGER_LITERAL",
                 "REAL_LITERAL",
                 "if",
                 "true",
                 "false",
                 "OclInvalid",
                 "while",
                 "compute",
                 "new",
                 "object",
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
                 "LBRACE",
                 "RBRACE",
                 "COMMA",
                 "COLON",
                 "COLONCOLON",
                 "BAR",
                 "log",
                 "in",
                 "var",
                 "assert",
                 "return",
                 "RESET_ASSIGN",
                 "LBRACKET",
                 "DOT",
                 "end",
                 "out",
                 "inout",
                 "NOT_EQUAL_EXEQ",
                 "main",
                 "ATPRE",
                 "EXCLAMATION_MARK",
                 "property",
                 "extends",
                 "RBRACKET",
                 "QUESTIONMARK",
                 "AT_SIGN",
                 "when",
                 "access",
                 "ARROW",
                 "CARET",
                 "CARETCARET",
                 "QUOTE_STRING_LITERAL",
                 "ADD_ASSIGN",
                 "INTEGER_RANGE_START",
                 "else",
                 "endif",
                 "implies",
                 "library",
                 "configuration",
                 "intermediate",
                 "refines",
                 "inherits",
                 "merges",
                 "disjuncts",
                 "EOF_TOKEN",
                 "DOTDOT",
                 "then",
                 "case",
                 "transformation",
                 "mapping",
                 "query",
                 "helper",
                 "population",
                 "blackbox",
                 "abstract",
                 "static",
                 "class",
                 "with",
                 "xselect",
                 "xcollect",
                 "selectOne",
                 "collectOne",
                 "collectselect",
                 "collectselectOne",
                 "forEach",
                 "forOne",
                 "import",
                 "metamodel",
                 "modeltype",
                 "uses",
                 "where",
                 "enforcing",
                 "rename",
                 "Dict",
                 "List",
                 "break",
                 "composes",
                 "constructor",
                 "continue",
                 "datatype",
                 "default",
                 "derived",
                 "do",
                 "elif",
                 "enum",
                 "except",
                 "exception",
                 "from",
                 "literal",
                 "ordered",
                 "primitive",
                 "raise",
                 "readonly",
                 "references",
                 "tag",
                 "try",
                 "typedef",
                 "unlimited",
                 "invalid",
                 "inv",
                 "pre",
                 "post",
                 "endpackage",
                 "def"
             };

    public final static boolean isValidForParser = true;
}
