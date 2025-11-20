package com.example.lab4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 1. 將元件宣告為類別屬性 (lateinit var)，避免在不同地方重複查找
    private lateinit var tvMeal: TextView
    private lateinit var btnChoice: Button

    // 2. 簡化 ActivityResultLauncher 的處理邏輯
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // 使用 early return 模式，當結果不 OK 或 Intent 為空時，立即退出
        if (result.resultCode != Activity.RESULT_OK || result.data == null) {
            return@registerForActivityResult
        }

        // 使用 result.data.let 確保 Intent 不為 null，並執行操作
        result.data.let { intent ->
            // 3. 使用 Elvis 操作符 (?:) 安全地取得字串，並提供預設值，避免 UI 顯示 "null"
            val drink = intent?.getStringExtra("drink") ?: "未知飲料"
            val sugar = intent?.getStringExtra("sugar") ?: "未知甜度"
            val ice = intent?.getStringExtra("ice") ?: "未知冰塊"

            // 直接使用已初始化的 tvMeal 屬性設定文字
            tvMeal.text = "飲料：$drink\n\n甜度：$sugar\n\n冰塊：$ice"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 4. 移除不必要的 EdgeToEdge 和 WindowInsets 處理，讓 onCreate 更簡潔
        setContentView(R.layout.activity_main)

        // 5. 初始化元件屬性
        tvMeal = findViewById(R.id.tvMeal)
        btnChoice = findViewById(R.id.btnChoice)

        // 6. 簡化 Button 點擊事件
        btnChoice.setOnClickListener {
            val intent = Intent(this, SecActivity::class.java)
            startForResult.launch(intent)
        }
    }
}