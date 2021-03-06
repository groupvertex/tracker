package ua.vertex.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.vertex.config.DBConfig;
import entity.WayPoint;
import ua.vertex.dao.waypoint.WayPointDAO;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DBConfig.class)
@ActiveProfiles("test")
public class WayPointDAOImplTest {

    @Autowired
    private WayPointDAO wayPointDAO;


    @Test
    public void createWayPoint() throws Exception {
        WayPoint actual = WayPoint.newBuilder().setRouteId(1).setX(10).setY(10).setHeight(100).setAccuracy(5).build();
        wayPointDAO.create(actual);
        Assert.assertEquals(1, actual.getRouteId());
        Assert.assertEquals(10, actual.getX(), 0.0001);
        Assert.assertEquals(10, actual.getY(), 0.0001);
        Assert.assertEquals(100, actual.getHeight());
        Assert.assertEquals(5, actual.getAccuracy());
    }

    @Test
    public void readWayPoint() throws Exception {
        WayPoint wayPoint = wayPointDAO.read(2);
        Assert.assertEquals(2, wayPoint.getId());
        Assert.assertEquals(1, wayPoint.getRouteId());
        Assert.assertEquals(50, wayPoint.getX(), 0.001);
        Assert.assertEquals(50, wayPoint.getY(), 0.001);
        Assert.assertEquals(405, wayPoint.getHeight());
        Assert.assertEquals(5, wayPoint.getAccuracy());
    }

    @Test
    public void updateWayPoint() throws Exception {
        WayPoint expected = WayPoint.newBuilder().setRouteId(3).setX(111).setY(111).setHeight(111).setAccuracy(111).build();
        wayPointDAO.update(1, expected);
        Assert.assertEquals(3, expected.getRouteId());
        Assert.assertEquals(111, expected.getX(), 0.001);
        Assert.assertEquals(111, expected.getY(), 0.001);
        Assert.assertEquals(111, expected.getHeight(), 111);
        Assert.assertEquals(111, expected.getAccuracy());
    }

    @Test(expected = Exception.class)
    public void deleteWayPoint() throws Exception {
        wayPointDAO.delete(1);
        wayPointDAO.read(1);
    }

    @Test
    public void getSortedWayPointsForRoute() throws Exception {
        List<WayPoint> list = wayPointDAO.getSortedWayPointsForRoute(3);
        assertEquals(2, list.size());
        Assert.assertEquals(3, list.get(0).getId());
        Assert.assertEquals(4, list.get(1).getId());
    }
}