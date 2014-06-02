package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

/**
 * @class SimpleAtomicLong
 *
 * @brief This class implements a subset of the
 *        java.util.concurrent.atomic.SimpleAtomicLong class using a
 *        ReentrantReadWriteLock to illustrate how they work.
 */
class SimpleAtomicLong
{
    /**
     * The value that's manipulated atomically via the methods.
     */
    private long mValue;
    
    /**
     * The ReentrantReadWriteLock used to serialize access to mValue.
     */

    // TODO -- you fill in here by replacing the null with an
    // initialization of ReentrantReadWriteLock.
    private ReentrantReadWriteLock mRWLock =new ReentrantReadWriteLock();

    //    mRWLock = new Rea
    /**
     * Creates a new SimpleAtomicLong with the given initial value.
     */
    public SimpleAtomicLong(long initialValue)
    {
        // TODO -- you fill in here
    	this.mValue=initialValue;
    }

    /**
     * @brief Gets the current value.
     * 
     * @returns The current value
     */
    public long get()
    {
        this.mRWLock.readLock().lock();
        try
        {
        	return this.mValue;
        }
        finally{
        	this.mRWLock.readLock().unlock();
        }
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the updated value
     */
    public long decrementAndGet()
    {
    	  this.mRWLock.writeLock().lock();
          try
          {
        	this.mValue--;
          	return this.mValue;
          }
          finally{
          	this.mRWLock.writeLock().unlock();
          }
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the previous value
     */
    public long getAndIncrement()
    {
        long value = get();                  
        this.mRWLock.writeLock().lock();
        try
        {
        	this.mValue++;
        	return value;
        }
        finally{
        	this.mRWLock.writeLock().unlock();
        }
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the previous value
     */
    public long getAndDecrement()
    {
    	 long value = get();                  
         this.mRWLock.writeLock().lock();
         try
         {
         	this.mValue--;
         	return value;
         }
         finally{
         	this.mRWLock.writeLock().unlock();
         }
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the updated value
     */
    public long incrementAndGet()
    {
      //  long value = get();
                
        this.mRWLock.writeLock().lock();
        try
        {
        	this.mValue++;
        	return this.mValue;
        }
        finally{
        	this.mRWLock.writeLock().unlock();
        }
    }
}

