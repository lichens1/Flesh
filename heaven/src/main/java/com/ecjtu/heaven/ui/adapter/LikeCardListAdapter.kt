package com.ecjtu.heaven.ui.adapter

import com.ecjtu.heaven.db.DatabaseManager
import com.ecjtu.heaven.db.table.impl.HistoryTableImpl
import com.ecjtu.netcore.model.PageModel

/**
 * Created by Ethan_Xiang on 2017/9/19.
 */
class LikeCardListAdapter(pageModel: PageModel) : CardListAdapter(pageModel) {

    private var mHistory: List<PageModel.ItemModel>? = null

    override fun onBindViewHolder(holder: VH?, position: Int) {
        super.onBindViewHolder(holder, position)
        if (mHistory == null) {
            val db = DatabaseManager.getInstance(holder?.itemView?.context)?.getDatabase()
            val impl = HistoryTableImpl()
            if (db != null) {
                mHistory = impl.getAllHistory(db)
            }
            db?.close()
        }

        if (mHistory != null) {
            val href = pageModel.itemList[position].href
            var index = 0
            for (url in mHistory!!) {
                if (href == url.href) {
                    index++
                }
            }
            holder?.description?.text = "查看${index}次"
        }
    }

    override fun onResume() {
        super.onResume()
        resetHistory()
    }

    fun resetHistory() {
        mHistory = null
    }
}