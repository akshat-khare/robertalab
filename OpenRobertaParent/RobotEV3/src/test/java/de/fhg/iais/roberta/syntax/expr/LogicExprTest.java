package de.fhg.iais.roberta.syntax.expr;

import org.junit.Test;

import de.fhg.iais.roberta.util.test.ev3.HelperEv3ForXmlTest;

public class LogicExprTest {
    private final HelperEv3ForXmlTest h = new HelperEv3ForXmlTest();

    @Test
    public void test1() throws Exception {
        String a =
            "\nfalse == true\n"
                + "true != false\n"
                + "false == false\n"
                + "(5 <= 7) == (8 > 9)\n"
                + "( 5 != 7 ) >= ( 8 == 9 )\n"
                + "(5 + 7) >= (( 8 + 4 ) / ((float) ( 9 + 3 )))\n"
                + "( (5 + 7) == (5 + 7) ) >= (( 8 + 4 ) / ((float) ( 9 + 3 )))\n"
                + "( (5 + 7) == (5 + 7) ) >= ( ((5 + 7) == (5 + 7)) && ((5 + 7) <= (5 + 7) ))\n"
                + "!((5 + 7) == (5 + 7)) == true}";
        this.h.assertCodeIsOk(a, "/syntax/expr/logic_expr.xml");
    }

    @Test
    public void logicNegate() throws Exception {
        String a = "\n!((0 != 0) && false)}";

        this.h.assertCodeIsOk(a, "/syntax/expr/logic_negate.xml");
    }

    @Test
    public void logicNull() throws Exception {
        String a = "\nnull}";

        this.h.assertCodeIsOk(a, "/syntax/expr/logic_null.xml");
    }

    @Test
    public void logicTernary() throws Exception {
        String a = "\n( 0 == 0 ) ? false : true}";

        this.h.assertCodeIsOk(a, "/syntax/expr/logic_ternary.xml");
    }
}