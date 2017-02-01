package com.stnetix.ariaddna.commonutils.exeption;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 01.02.17.
 */
public class AriaddnaExeptionTest {
    @org.junit.Test
    public void getErrorMessageManyLevels() throws Exception {
        Throwable t1 = new AriaddnaExeption("1 уровень");
        Throwable t2 = new AriaddnaExeption("2 уровень", t1);
        Throwable t3 = new AriaddnaExeption("3 уровень", t2);
        Throwable t4 = new AriaddnaExeption("4 уровень", t3);
        Throwable t5 = new AriaddnaExeption("5 уровень", t4);
        Throwable t6 = new AriaddnaExeption("6 уровень", t5);
        Throwable t7 = new AriaddnaExeption("7 уровень", t6);
        Throwable t8 = new AriaddnaExeption("8 уровень", t7);
        Throwable t9 = new AriaddnaExeption("9 уровень", t8);
        try {
            throw t9;
        }catch (AriaddnaExeption e){
            System.out.println(e.getErrorMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void getErrorMessageOneLevel(){
        Throwable t1 = new AriaddnaExeption("1 уровень");
        Throwable t2 = new AriaddnaExeption("2 уровень", t1);
        try {
            throw t2;
        }catch (AriaddnaExeption e){
            System.out.println(e.getErrorMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void getErrorMessageTwoLevel(){
        Throwable t1 = new AriaddnaExeption("1 уровень");
        try {
            throw t1;
        }catch (AriaddnaExeption e){
            System.out.println(e.getErrorMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}