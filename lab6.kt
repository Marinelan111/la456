package com.example.lab6

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // 建立要顯示在列表上的字串陣列 (移至類別屬性)
    private val dialogItems = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. 移除不必要的 EdgeToEdge 和 WindowInsets 處理
        setContentView(R.layout.activity_main)

        // 2. 使用 Lambda 表達式和獨立方法簡化按鈕邏輯
        findViewById<Button>(R.id.btnToast).setOnClickListener {
            showToast("預設 Toast")
        }

        findViewById<Button>(R.id.btnSnackBar).setOnClickListener {
            showSnackBar(it)
        }

        findViewById<Button>(R.id.btnDialog1).setOnClickListener {
            showButtonDialog()
        }

        findViewById<Button>(R.id.btnDialog2).setOnClickListener {
            showListDialog()
        }

        findViewById<Button>(R.id.btnDialog3).setOnClickListener {
            showSingleChoiceDialog()
        }
    }

    /** 建立 showToast 方法，顯示 Toast 訊息 */
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    /** 處理 Snackbar 邏輯 */
    private fun showSnackBar(view: View) {
        Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
            .setAction("按鈕") {
                showToast("已回應")
            }.show()
    }

    /** 處理 Dialog 1: 按鈕式 */
    private fun showButtonDialog() {
        AlertDialog.Builder(this)
            .setTitle("按鈕式 AlertDialog")
            .setMessage("AlertDialog 內容")
            .setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
            .setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
            .setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
            .show()
    }

    /** 處理 Dialog 2: 列表式 */
    private fun showListDialog() {
        AlertDialog.Builder(this)
            .setTitle("列表式 AlertDialog")
            .setItems(dialogItems) { _, i ->
                showToast("你選的是${dialogItems[i]}")
            }.show()
    }

    /** 處理 Dialog 3: 單選式 */
    private fun showSingleChoiceDialog() {
        var position = 0 // 宣告變數 position 用來記錄選擇的項目

        AlertDialog.Builder(this)
            .setTitle("單選式 AlertDialog")
            // 預設選擇第一個項目
            .setSingleChoiceItems(dialogItems, 0) { _, i ->
                // 更新變數 position 的值
                position = i
            }
            .setPositiveButton("確定") { _, _ ->
                showToast("你選的是${dialogItems[position]}")
            }
            .show()
    }
}
