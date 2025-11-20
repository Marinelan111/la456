package com.example.lab5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 填充 Layout 佈局，返回 View 對象，這是 Fragment 顯示 UI 所必需的。
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
}