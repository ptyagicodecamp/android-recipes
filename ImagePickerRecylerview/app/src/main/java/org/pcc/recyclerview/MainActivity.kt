package org.pcc.recyclerview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    val PICK_IMAGE_MULTIPLE = 1
    var imageEncoded: String? = null
    var imagesEncodedList: MutableList<String> = mutableListOf()


    var fabSelectImage: FloatingActionButton? = null
    var recyclerView: RecyclerView? = null
    var mAdapter: MyRecyclerAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabSelectImage = findViewById(R.id.fabPickImages) as FloatingActionButton
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView!!.setHasFixedSize(true)
        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        recyclerView!!.setLayoutManager(layoutManager)

        fabPickImages.setOnClickListener {
            var intent = Intent()
            intent.setType("image/*")
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select Pictures"), PICK_IMAGE_MULTIPLE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE
                && resultCode == RESULT_OK
                && null != data) {
                // Get the Image from data

                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                imagesEncodedList = ArrayList()
                if (data.data != null) {

                    val mImageUri = data.data

                    // Get the cursor
                    val cursor = contentResolver.query(
                        mImageUri,
                        filePathColumn, null, null, null
                    )
                    // Move to first row
                    cursor.moveToFirst()

                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    imageEncoded = cursor.getString(columnIndex)
                    cursor.close()

                    val mArrayUri = ArrayList<Uri>()
                    mArrayUri.add(mImageUri)

                    // specify an adapter (see also next example)
                    mAdapter = MyRecyclerAdapter(applicationContext, mArrayUri)
                    recyclerView!!.adapter = mAdapter
                } else {
                    if (data.clipData != null) {
                        val mClipData = data.clipData
                        val mArrayUri = ArrayList<Uri>()
                        for (i in 0 until mClipData!!.itemCount) {

                            val item = mClipData.getItemAt(i)
                            val uri = item.uri
                            mArrayUri.add(uri)
                            // Get the cursor
                            val cursor = contentResolver.query(uri, filePathColumn, null, null, null)
                            // Move to first row
                            cursor.moveToFirst()

                            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                            imageEncoded = cursor.getString(columnIndex)
                            if (imageEncoded != null) imagesEncodedList.add(imageEncoded!!)
                            cursor.close()
                            mAdapter = MyRecyclerAdapter(applicationContext, mArrayUri)
                            recyclerView!!.adapter = mAdapter

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size)
                    }
                }
            } else {
                Toast.makeText(
                    this, "You haven't picked Image",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                .show()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
