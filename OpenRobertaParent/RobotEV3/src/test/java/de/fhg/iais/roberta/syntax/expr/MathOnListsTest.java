package de.fhg.iais.roberta.syntax.expr;

import org.junit.Test;

import de.fhg.iais.roberta.util.test.ev3.HelperEv3ForXmlTest;

public class MathOnListsTest {
    private final HelperEv3ForXmlTest h = new HelperEv3ForXmlTest();

    @Test
    public void mathOnListSum() throws Exception {
        String a = "BlocklyMethods.sumOnList(BlocklyMethods.createListWithNumber(5,3,2))}";

        this.h.assertCodeIsOk(a, "/syntax/math/math_on_list_sum.xml");
    }

    @Test
    public void mathOnListMin() throws Exception {
        String a = "BlocklyMethods.minOnList(BlocklyMethods.createListWithNumber(5,3,2))}";

        this.h.assertCodeIsOk(a, "/syntax/math/math_on_list_min.xml");
    }

    @Test
    public void mathOnListMax() throws Exception {
        String a = "BlocklyMethods.maxOnList(BlocklyMethods.createListWithNumber(5,3,2))}";

        this.h.assertCodeIsOk(a, "/syntax/math/math_on_list_max.xml");
    }

    @Test
    public void mathOnListAverage() throws Exception {
        String a = "BlocklyMethods.averageOnList(BlocklyMethods.createListWithNumber(5,3,2))}";

        this.h.assertCodeIsOk(a, "/syntax/math/math_on_list_average.xml");
    }

    @Test
    public void mathOnListMedian() throws Exception {
        String a = "BlocklyMethods.medianOnList(BlocklyMethods.createListWithNumber(5,3,2))}";

        this.h.assertCodeIsOk(a, "/syntax/math/math_on_list_median.xml");
    }

    @Test
    public void mathOnListStandardDeviatioin() throws Exception {
        String a = "BlocklyMethods.standardDeviatioin(BlocklyMethods.createListWithNumber(5,3,2))}";

        this.h.assertCodeIsOk(a, "/syntax/math/math_on_list_std_dev.xml");
    }

    @Test
    public void mathOnListRandom() throws Exception {
        String a = "BlocklyMethods.randOnList(BlocklyMethods.createListWithNumber(5,3,2))}";

        this.h.assertCodeIsOk(a, "/syntax/math/math_on_list_random.xml");
    }

    @Test
    public void mathOnListMode() throws Exception {
        String a = "BlocklyMethods.modeOnList(BlocklyMethods.createListWithNumber(5,3,2))}";

        this.h.assertCodeIsOk(a, "/syntax/math/math_on_list_mode.xml");
    }

}
