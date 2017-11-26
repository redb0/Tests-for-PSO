import org.easymock.EasyMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class Tests {
    private AlgorithmParameters algParam;
    private TestFunctions testFunc;

    @BeforeEach
    void setUp() {
        this.algParam = new AlgorithmParameters(1, 1, 1, 1);
        testFunc = EasyMock.createMock(TestFunctions.class);
    }

    @Test
    void test_initializationPoint() {
        SearchArea searchArea = EasyMock.createMock(SearchArea.class);
        Point pointMock = EasyMock.createMock(Point.class);

        PSO_alg pso = new PSO_alg();

        double[] a = {10, 10};
        double[] b = {0, 0};
        double[] coordinates = {1.0, 1.0};

        //действия в конструкторе
        EasyMock.expect(searchArea.getRestrictionTop())
                .andStubReturn(a);
        EasyMock.expect(searchArea.getRestrictionBottom())
                .andStubReturn(b);
        EasyMock.replay(searchArea);

        EasyMock.expect(testFunc.getValueTestFunction( (double[]) EasyMock.anyObject() ))
                .andStubReturn(5.0);

        EasyMock.expect(pointMock.getCoordinates())
                .andStubReturn(coordinates);
        EasyMock.replay(pointMock);
        EasyMock.replay(testFunc);

        //конструктор
        Point p = EasyMock.createMockBuilder(Point.class)
                .withConstructor(SearchArea.class, TestFunctions.class)
                .withArgs(searchArea, testFunc)
                .createMock();

        EasyMock.replay(p);

        //проверка на правильность генерации координат
        ArrayList<Point> res = pso.initializationPoint(algParam, searchArea, testFunc);

        coordinates = res.get(0).getCoordinates();

        for (int i = 0; i < coordinates.length; i++) {
            assertTrue((coordinates[i] >= 0) && (coordinates[i] <= 10));
        }
    }

    @Test
    void test_findEvaluateFunction() {
        Point pointMock = EasyMock.createNiceMock(Point.class);
        ArrayList<Point> p = new ArrayList<Point>();
        p.add(pointMock);

        PSO_alg pso = new PSO_alg();
        double[] coordinates = {1.0, 1.0};

        EasyMock.expect(testFunc.getValueTestFunction( (double[]) EasyMock.anyObject() ))
                .andStubReturn(5.0);

        EasyMock.expect(p.get(0).getCoordinates())
                .andStubReturn(coordinates);

        EasyMock.replay(p.get(0));
        EasyMock.replay(testFunc);

        pointMock.setFitnessValue(5.0);
//        EasyMock.expectLastCall().atLeastOnce();
//        EasyMock.expectLastCall().anyTimes();
        //EasyMock.expectLastCall();

//        EasyMock.replay(pointMock);

        double[] fitnessValues = pso.findEvaluateFunction(p, testFunc);

        assertTrue(fitnessValues[0] == 5.0);
    }
}
