package com.bawei.shenmengkai.bean;

import java.util.List;

public class MyBean {
    /**
     * code : 200
     * orderData : [{"shopId":1,"shopName":"姣忔棩钄矞涓撳崠搴�","cartlist":[{"id":1,"shopId":1,"shopName":"姣忔棩钄矞涓撳崠搴�","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E8%8F%A0%E8%8F%9C.png","productId":1,"productName":"鑿犺彍","color":"棰滆壊锛氱櫧鑹�","meal":"濂楅锛氶粯璁ゅ椁�","price":3,"count":1},{"id":2,"shopId":1,"shopName":"姣忔棩钄矞涓撳崠搴�","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E8%83%A1%E8%90%9D%E5%8D%9C.png","productId":2,"productName":"鑳¤悵鍗�","color":"棰滆壊锛氱孩鑹�","meal":"濂楅锛氶粯璁ゅ椁�","price":4,"count":1}]},{"shopId":2,"shopName":"楦¤泲涓撳崠搴�","cartlist":[{"id":1,"shopId":2,"shopName":"楦¤泲涓撳崠搴�","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E9%B8%A1%E8%9B%8B.png","productId":1,"productName":"楦¤泲","color":"棰滆壊锛氱櫧鑹�","meal":"濂楅锛氶粯璁ゅ椁�","price":10,"count":1}]}]
     */

    private int code;
    private List<OrderDataBean> orderData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<OrderDataBean> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<OrderDataBean> orderData) {
        this.orderData = orderData;
    }

    public static class OrderDataBean {
        /**
         * shopId : 1
         * shopName : 姣忔棩钄矞涓撳崠搴�
         * cartlist : [{"id":1,"shopId":1,"shopName":"姣忔棩钄矞涓撳崠搴�","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E8%8F%A0%E8%8F%9C.png","productId":1,"productName":"鑿犺彍","color":"棰滆壊锛氱櫧鑹�","meal":"濂楅锛氶粯璁ゅ椁�","price":3,"count":1},{"id":2,"shopId":1,"shopName":"姣忔棩钄矞涓撳崠搴�","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E8%83%A1%E8%90%9D%E5%8D%9C.png","productId":2,"productName":"鑳¤悵鍗�","color":"棰滆壊锛氱孩鑹�","meal":"濂楅锛氶粯璁ゅ椁�","price":4,"count":1}]
         */

        private int shopId;
        private String shopName;
        private List<CartlistBean> cartlist;
        private Boolean Status = false;

        public Boolean getStatus() {
            return Status;
        }

        public void setStatus(Boolean status) {
            Status = status;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public List<CartlistBean> getCartlist() {
            return cartlist;
        }

        public void setCartlist(List<CartlistBean> cartlist) {
            this.cartlist = cartlist;
        }

        public static class CartlistBean {
            /**
             * id : 1
             * shopId : 1
             * shopName : 姣忔棩钄矞涓撳崠搴�
             * defaultPic : http://blog.zhaoliang5156.cn/api/images/%E8%8F%A0%E8%8F%9C.png
             * productId : 1
             * productName : 鑿犺彍
             * color : 棰滆壊锛氱櫧鑹�
             * meal : 濂楅锛氶粯璁ゅ椁�
             * price : 3
             * count : 1
             */

            private int id;
            private int shopId;
            private String shopName;
            private String defaultPic;
            private int productId;
            private String productName;
            private String color;
            private String meal;
            private int price;
            private int count;
            private Boolean status = false;

            public Boolean getStatus() {
                return status;
            }

            public void setStatus(Boolean status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getDefaultPic() {
                return defaultPic;
            }

            public void setDefaultPic(String defaultPic) {
                this.defaultPic = defaultPic;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getMeal() {
                return meal;
            }

            public void setMeal(String meal) {
                this.meal = meal;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
