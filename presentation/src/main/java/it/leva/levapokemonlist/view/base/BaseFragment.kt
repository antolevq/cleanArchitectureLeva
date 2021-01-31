package it.leva.levapokemonlist.view.base

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected var progressLayout: ConstraintLayout? = null
    protected var mainLayout: ConstraintLayout? = null
    protected var errorLayout: ConstraintLayout? = null
    protected var txtErrorMessage: TextView? = null

    internal fun showProgress() {
        changeProgressStatus(true)
    }

    internal fun hideProgress() {
        changeProgressStatus(false)
    }

    internal fun showError(errorMessage: String) {
        hideProgress()
        txtErrorMessage?.text = errorMessage
        errorLayout?.visibility = View.VISIBLE
        mainLayout?.visibility = View.GONE
    }


    private fun changeProgressStatus(isLoading: Boolean) {
        progressLayout?.visibility = if (isLoading) View.VISIBLE else View.GONE
        mainLayout?.visibility = if (isLoading) View.GONE else View.VISIBLE
    }


}