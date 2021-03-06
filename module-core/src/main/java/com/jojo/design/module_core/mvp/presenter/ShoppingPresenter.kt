package com.jojo.design.module_core.mvp.presenter

import com.jojo.design.common_base.dagger.mvp.BasePresenter
import com.jojo.design.common_base.net.RetrofitManager
import com.jojo.design.common_base.net.RxObserverListener
import com.jojo.design.module_core.bean.*
import com.jojo.design.module_core.mvp.contract.DesignerContract
import com.jojo.design.module_core.mvp.contract.ShoppingContract
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 10:09 PM
 *    desc   : 逛
 */
class ShoppingPresenter @Inject constructor() : BasePresenter<ShoppingContract.View, ShoppingContract.Model>(), ShoppingContract.Presenter {
    override fun getCategoryList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getCategoryList(), object : RxObserverListener<List<CategoryEntity>>(mView) {
            override fun onSuccess(result: List<CategoryEntity>?) {
                mView?.getCategoryList(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }

    override fun getGoodsList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequestOther(mModel!!.getGoodsList(), object : RxObserverListener<List<GoodsEntity>>(mView) {
            override fun onSuccess(result: List<GoodsEntity>?) {
                mView?.getGoodsList(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }

    override fun getHandPickedGoods(page: String) {
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getHandPickedGoods(page), object : RxObserverListener<RecordsEntity>(mView) {
            override fun onSuccess(result: RecordsEntity?) {
                mView?.getHandPickedGoods(result!!)
            }
        }))
    }

    override fun getPersonLike() {
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getPersonLike(), object : RxObserverListener<List<AllfaverEntity>>(mView) {
            override fun onSuccess(result: List<AllfaverEntity>?) {
                mView?.getPersonLike(result!!)
            }
        }))
    }
}