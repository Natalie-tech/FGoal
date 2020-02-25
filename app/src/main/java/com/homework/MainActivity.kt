package com.homework

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homework.adapter.FirstLevelAdapter
import com.homework.models.Level1Node
import com.homework.models.Level2Node
import com.homework.models.Level2NodeContent

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView:RecyclerView
    lateinit var itemList:ArrayList<Level1Node>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        prepareData()
        showList()
    }


    private fun prepareData(){
        itemList = ArrayList<Level1Node>()

        val firstNodeLevel2Node1Content = Level2NodeContent("$800.00","2023/2/10","2020/4/1")
        val firstNodeLevel2Node1 = Level2Node("Travel to Boston", firstNodeLevel2Node1Content, false)

        val firstNodeLevel2Node2Content = Level2NodeContent("$800.00","2023/2/10","2020/4/1")
        val firstNodeLevel2Node2 = Level2Node("Audi A8", firstNodeLevel2Node2Content, false)

        val firstNodeLevel2Node3Content = Level2NodeContent("$800.00","2023/2/10","2020/4/1")
        val firstNodeLevel2Node3 = Level2Node("Wedding Party 2021", firstNodeLevel2Node3Content, false)

        var firstNodeChilds = ArrayList<Level2Node>()
        firstNodeChilds.add(firstNodeLevel2Node1)
        firstNodeChilds.add(firstNodeLevel2Node2)
        firstNodeChilds.add(firstNodeLevel2Node3)


        val secondNodeLevel2Node1Content = Level2NodeContent("$800.00","2023/2/10","2020/4/1")
        val secondNodeLevel2Node1 = Level2Node("Buy a Big House in NYC", secondNodeLevel2Node1Content, false)

        val secondNodeLevel2Node2Content = Level2NodeContent("$800.00","2023/2/10","2020/4/1")
        val secondNodeLevel2Node2 = Level2Node("Education in MIT", secondNodeLevel2Node2Content, false)

        var secondNodeChilds = ArrayList<Level2Node>()
        secondNodeChilds.add(secondNodeLevel2Node1)
        secondNodeChilds.add(secondNodeLevel2Node2)


        val thirdNodeLevel2Node1Content = Level2NodeContent("$800.00","2023/2/10","2020/4/1")
        val thirdNodeLevel2Node1 = Level2Node("BOA credit card", thirdNodeLevel2Node1Content, false)

        val thirdNodeLevel2Node2Content = Level2NodeContent("$800.00","2023/2/10","2020/4/1")
        val thirdNodeLevel2Node2 = Level2Node("Load for MIT education", thirdNodeLevel2Node2Content, false)

        var thirdNodeChilds = ArrayList<Level2Node>()
        thirdNodeChilds.add(thirdNodeLevel2Node1)
        thirdNodeChilds.add(thirdNodeLevel2Node2)

        itemList.add(Level1Node("Short-Term Goal", firstNodeChilds))
        itemList.add(Level1Node("Long-Term Goal", secondNodeChilds))
        itemList.add(Level1Node("Liabilities", thirdNodeChilds))
    }

    private fun showList(){
        val level1NodeAdapter = FirstLevelAdapter(this, itemList)

        recyclerView.adapter = level1NodeAdapter
    }
}
