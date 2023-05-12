package com.example.project_kotlin.screens

import android.graphics.BitmapFactory
import android.util.Log
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.project_kotlin.database.DBHelper
import com.example.project_kotlin.database.testdata.*
import com.example.project_kotlin.databinding.FragmentHomeBinding
import com.example.project_kotlin.domain.ExtraImage
import kotlinx.coroutines.*

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.btnCreateDB.setOnClickListener {
            DBHelper.init(requireContext())
            binding.txtOutput.text = "Database gecreëerd!"
        }

        binding.btnInsertIntoDB.setOnClickListener {
            binding.txtOutput.text = "Data toevoegen..."
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    fillDb()
                    withContext(Dispatchers.Main) {
                        binding.txtOutput.text = "Data ingevoerd!"
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        binding.txtOutput.text = "Databank nog niet aangemaakt!"
                    }
                }

            }
        }

        binding.btnInsertExtraImages.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    ExtraImageMock(requireContext()).insertMocks()
                    withContext(Dispatchers.Main) {
                        binding.txtOutput.text = "Images toegevoegd!"
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        binding.txtOutput.text = "Databank nog niet aangemaakt!"
                    }
                }

            }
        }

        binding.btnSshowExtraImages.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    var images = ExtraImageMock(requireContext()).getMocks()
                    withContext(Dispatchers.Main) {
                        displayImages(images)
                    }
                    Log.i("wa", "hierzooooooooooooooooooooooo")
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.e("wa", e.toString())
                        binding.txtOutput.text = "Databank nog niet aangemaakt!"
                    }
                }
            }
        }

    }

    private fun displayImages(images: List<ExtraImage>) {
        var i = 1
        for (extraImage in images) {
            Log.i("wa", "${i++}")
            val imageView = ImageView(requireContext())
            val bitmap = BitmapFactory.decodeByteArray(extraImage.image, 0, extraImage.image.size)
            imageView.setImageBitmap(bitmap)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.bottomMargin = 32

            binding.linearScrollImages.addView(imageView, layoutParams)
        }
    }


    private fun fillDb() {
        IngredientCategoryMock.insertMocks()
        IngredientMock.insertMocks()
        ShoppingListMock.insertMocks()
        DishMock(requireContext()).insertMocks()
        MealPlanMock.insertMocks()
    }

}