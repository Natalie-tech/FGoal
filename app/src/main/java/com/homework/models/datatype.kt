package com.homework.models

/**
 * 2级节点内容
 */
data class Level2NodeContent(val payment:String, val targetDate:String, val startDate:String)

/**
 * 2级节点主体,isContentOpen代表内容是否处于打开状态，需要用该状态来计算高度
 */
data class Level2Node(val title:String, val content:Level2NodeContent, var isContentOpen: Boolean)

/**
 * 顶级节点
 */
data class Level1Node(val title:String, val childs:List<Level2Node>)