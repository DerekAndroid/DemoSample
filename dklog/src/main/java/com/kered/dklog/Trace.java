package com.kered.dklog;

public class Trace {
    public static String getCurrentMethod() {
        return getCurrentMethodNameFromThread(0);
    }

    private static String getCurrentMethodNameFromThread(int stackLevel) {
        /*
         * 0 - dumpThreads
         * 1 - getStackTrace
         * 2 - thisMethod => getCurrentMethodNameFromThread
         * 3 - callingMethod => method calling thisMethod 
         * 4 - method calling callingMethod
         */
        StackTraceElement stackTraceElement =  Thread.currentThread().getStackTrace()[4 + stackLevel];

        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
      
        // -- full className
        //return className + "#" + methodName + " -> ";
        
        String mName = className.substring(className.lastIndexOf(".") + 1, className.length());
        return mName + "#" + methodName + " -> ";
    }
}
