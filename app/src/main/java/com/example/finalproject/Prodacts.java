    package com.example.finalproject;

    public class Prodacts {

        private String nameProduct ;
        private int idProduct ;
        private int   productPs ;
        private double priceProduct ;
        private byte[] image;

        private String ProductPriceCount;
        private  int ps ;



        public Prodacts(String nameProduct, double priceProduct, byte[] image , int ps) {
            this.nameProduct = nameProduct;
            this.priceProduct = priceProduct;
            this.image = image;
            this.ps = ps;

        }

        public String getProductPriceCount() {
            return ProductPriceCount;
        }

        public void setProductPriceCount(String productPriceCount) {
            ProductPriceCount = productPriceCount;
        }

        public Prodacts(String nameProduct, Double priceProduct, int ps) {
            this.nameProduct = nameProduct;
            this.priceProduct = priceProduct;

            this.ps = ps;

        }

        public Prodacts(String nameProduct, Double priceProduct) {
            this.nameProduct = nameProduct;
            this.priceProduct = priceProduct;

        }



        public String getNameProduct() {
            return nameProduct;
        }

        public void setNameProduct(String nameProduct) {
            this.nameProduct = nameProduct;
        }

        public int getIdProduct() {
            return idProduct;
        }

        public void setIdProduct(int idProduct) {
            this.idProduct = idProduct;
        }

        public Double getPriceProduct() {
            return priceProduct;
        }

        public void setPriceProduct(Double priceProduct) {
            this.priceProduct = priceProduct;
        }

        public byte[] getImage() {
            return image;
        }
        public int getProductPs() {
            return productPs;
        }

        public void setProductPs(int productPs) {
            this.productPs = productPs;
        }


        public void setImage(byte[] image) {

            this.image = image;
        }
    }
