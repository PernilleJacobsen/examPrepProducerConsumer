/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprepproducerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pernille
 */
public class ExamPrepProducerConsumer
{

    private static final BlockingQueue<Integer> s1 = new ArrayBlockingQueue(11);
    private static final BlockingQueue<Integer> s2 = new ArrayBlockingQueue(11);
    private static long result;
    private static long totalSum;

    public static void main(String[] args)
    {
        s1.add(4);
        s1.add(5);
        s1.add(8);
        s1.add(12);
        s1.add(21);
        s1.add(22);
        s1.add(34);
        s1.add(35);
        s1.add(36);
        s1.add(37);
        s1.add(42);
        P1 p1 = new P1();
        p1.start();
        P2 p2 = new P2();
        p2.start();
        P3 p3 = new P3();
        p3.start();
        P4 p4 = new P4();
        p4.start();
        C1 c1 = new C1();
        c1.start();
        

    }

    public static class P1 extends Thread
    {

        public void run()
        {
            while (!s1.isEmpty())
            {
                long n = s1.poll();
                System.out.println("p1 n er " + n);
                result = fib(n);
                try
                {
                    s2.put((int) result);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ExamPrepProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("p 1 result er : " + result);
            }
            //jeg mangler at stoppe tråden 
      
        }
    }

    public static class P2 extends Thread
    {

        public void run()
        {

            while (!s1.isEmpty())
            {
                long n = s1.poll();
                System.out.println("p2 n er " + n);
                result = fib(n);
                try
                {
                    s2.put((int) result);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ExamPrepProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("p2 result er : " + result);
            }
            
        }
    }

    public static class P3 extends Thread
    {
        public void run()
        {
            while (!s1.isEmpty())
            {
                long n = s1.poll();
                System.out.println("p3 n er " + n);
                result = fib(n);
                try
                {
                    s2.put((int) result);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ExamPrepProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("p3 result er : " + result);
            }
         
        }
    }

    public static class P4 extends Thread
    {
        public void run()
        {
            while (!s1.isEmpty())
            {
                long n = s1.poll();
                System.out.println("p4 n er " + n);
                result = fib(n);
                try
                {
                    s2.put((int) result);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ExamPrepProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("p4 result er : " + result);
            }
      
        }
    }

    public static class C1 extends Thread
    {
        public void run()
        {
            while (!s2.isEmpty())
            {
                try
                {
                    long take;
                    take = s2.take();
                    System.out.println("Tal fra S2 er: " + take);
                    totalSum = totalSum + take;
                    System.out.println("Total sum er: " + totalSum);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ExamPrepProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    private static synchronized long fib(long n)// hvor n er tallet hentet i s1
    {
        if ((n == 0) || (n == 1))
        {
            return n;
        } else
        {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
