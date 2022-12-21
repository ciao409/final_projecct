package com.example.myapplication.beam

/**
 * 賬單實體類
 *
 * @param remake 賬單備註
 * @param data 旅行團員json數據
 * @param time 入庫時間
 * */
data class BillBean(val id : Int, val remake : String, val data: String, val time : Long)