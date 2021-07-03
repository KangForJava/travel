package cn.kjy.travel.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 收藏实体类
 */
public class Favorite implements Serializable {
    private Route route;
    private String date;
    private User user;

    /**
     * 无参构造方法
     */
    public Favorite() {
        this.route = new Route();
    }

    /**
     * 有参构造方法
     * @param route 路线对象
     * @param date 收藏时间
     * @param user 用户
     */
    public Favorite(Route route, String date, User user) {
            this.route = route;
            this.date = date;
            this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<RouteImg> getRouteImgList() {
        return route.getRouteImgList();
    }

    public void setRouteImgList(List<RouteImg> routeImgList) {
        route.setRouteImgList(routeImgList);
    }

    public Category getCategory() {
        return route.getCategory();
    }

    public void setCategory(Category category) {
        route.setCategory(category);
    }

    public Seller getSeller() {
        return route.getSeller();
    }

    public void setSeller(Seller seller) {
        route.setSeller(seller);
    }

    public String getSourceId() {
        return route.getSourceId();
    }

    public void setSourceId(String sourceId) {
        route.setSourceId(sourceId);
    }

    public int getRid() {
        return route.getRid();
    }

    public void setRid(int rid) {
        route.setRid(rid);
    }

    public String getRname() {
        return route.getRname();
    }

    public void setRname(String rname) {
        route.setRname(rname);
    }

    public double getPrice() {
        return route.getPrice();
    }

    public void setPrice(double price) {
        route.setPrice(price);
    }

    public String getRouteIntroduce() {
        return route.getRouteIntroduce();
    }

    public void setRouteIntroduce(String routeIntroduce) {
        route.setRouteIntroduce(routeIntroduce);
    }

    public String getRflag() {
        return route.getRflag();
    }

    public void setRflag(String rflag) {
        route.setRflag(rflag);
    }

    public String getRdate() {
        return route.getRdate();
    }

    public void setRdate(String rdate) {
        route.setRdate(rdate);
    }

    public String getIsThemeTour() {
        return route.getIsThemeTour();
    }

    public void setIsThemeTour(String isThemeTour) {
        route.setIsThemeTour(isThemeTour);
    }

    public int getCount() {
        return route.getCount();
    }

    public void setCount(int count) {
        route.setCount(count);
    }

    public int getCid() {
        return route.getCid();
    }

    public void setCid(int cid) {
        route.setCid(cid);
    }

    public String getRimage() {
        return route.getRimage();
    }

    public void setRimage(String rimage) {
        route.setRimage(rimage);
    }

    public int getSid() {
        return route.getSid();
    }

    public void setSid(int sid) {
        route.setSid(sid);
    }
}
