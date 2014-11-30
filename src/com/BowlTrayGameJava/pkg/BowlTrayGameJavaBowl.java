package com.BowlTrayGameJava.pkg;

class BowlTrayGameJavaBowl {
	BowlTrayGameJavaBowl()  {this.seeds  =  0;}
    public  int  getSeeds()  {return  seeds;}
    public  void  addSeeds(int  seeds) {this.seeds += seeds;}
    public  boolean  isEmpty() {return seeds == 0;}
    public  int  removeSeeds() {
        int  seeds  =  this.seeds;
        this.seeds  =  0;
        return  seeds;
    }
    int  seeds;
}
