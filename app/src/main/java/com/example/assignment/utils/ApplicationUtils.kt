package com.example.assignment.utils


import java.text.DecimalFormat
import java.text.SimpleDateFormat


class ApplicationUtils {
    companion object {
        fun decimal(amount: Double): String {
            val df = DecimalFormat("#.00")
            val amtChg = df.format(amount)
            return amtChg.toString()
        }
        fun getdateTime(mDayTimeValue : Long): String {
            val df = SimpleDateFormat("hh:mm a")
            val date = df.format(mDayTimeValue)
            return date
        }

        fun getDateDifference(date :Long) : String{
            var time =""
            val diff: Long = System.currentTimeMillis() - date
            val seconds = diff / 1000
            val minutes = seconds / 60

            if(seconds<60){
                if(seconds<5){
                    time = "${"A few seconds ago"}"
                }
                else{
                    time = "${"A " +seconds+ " seconds ago"}"
                }

            }
            else if(seconds>60 && seconds<3600){
                time ="${"A "+minutes+ " minutes ago"}"
            }
            else{
                time = getdateTime(diff)
            }
            return time
        }
    }
}






