package presentation.extension

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/*
 * Commits the given transaction with the targeted fragment manager
 */
inline fun FragmentManager.transact(fmTransaction: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().fmTransaction().commit()
}
