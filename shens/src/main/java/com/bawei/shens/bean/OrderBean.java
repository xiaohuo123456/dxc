package com.bawei.shens.bean;

import java.util.List;

public class OrderBean {

    private String message;
    private String status;
    private List<OrderListBean> orderList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":27,"commodityName":"休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/5.jpg","commodityPrice":139,"orderDetailId":21794},{"commentStatus":1,"commodityCount":2,"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/2.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/3.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/4.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/5.jpg","commodityPrice":88,"orderDetailId":21795},{"commentStatus":1,"commodityCount":1,"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","commodityPic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/5.jpg","commodityPrice":278,"orderDetailId":21796},{"commentStatus":1,"commodityCount":7,"commodityId":17,"commodityName":"化妆镜","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/5.jpg","commodityPrice":31,"orderDetailId":21797},{"commentStatus":1,"commodityCount":4,"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/5.jpg,","commodityPrice":39,"orderDetailId":21798},{"commentStatus":1,"commodityCount":1,"commodityId":15,"commodityName":"玻儿精灵美妆蛋一个","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/5/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/5/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/5/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/5/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/5/5.jpg","commodityPrice":6,"orderDetailId":21799},{"commentStatus":1,"commodityCount":1,"commodityId":11,"commodityName":"盒装葫芦粉扑美妆蛋化妆海绵","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/5.jpg","commodityPrice":9,"orderDetailId":21800},{"commentStatus":1,"commodityCount":2,"commodityId":13,"commodityName":"贝览得美妆蛋","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/3/5.jpg","commodityPrice":44,"orderDetailId":21801}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 2019121313451049810935
         * orderStatus : 1
         * orderTime : 1576215910000
         * payAmount : 1069
         * payMethod : 1
         * userId : 10935
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private long orderTime;
        private int payAmount;
        private int payMethod;
        private int userId;
        private List<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public long getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(long orderTime) {
            this.orderTime = orderTime;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "OrderListBean{" +
                    "expressCompName='" + expressCompName + '\'' +
                    ", expressSn='" + expressSn + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", orderStatus=" + orderStatus +
                    ", orderTime=" + orderTime +
                    ", payAmount=" + payAmount +
                    ", payMethod=" + payMethod +
                    ", userId=" + userId +
                    ", detailList=" + detailList +
                    '}';
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * commentStatus : 1
             * commodityCount : 1
             * commodityId : 27
             * commodityName : 休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋
             * commodityPic : http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/5.jpg
             * commodityPrice : 139
             * orderDetailId : 21794
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }
    }
}
