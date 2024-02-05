package fairy.fairy_eye

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import fairy.fairy_eye.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val cameraFragment = CameraFragment()
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val transition: FragmentTransaction = fragmentManager.beginTransaction()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)

        setSupportActionBar(binding.toolbar.toolbar)


        val array = ArrayList<String>().apply {
            add("Item 1")
            add("Item 2")
            add("Item 3")
            add("Item 4")
            add("Item 5")
            add("Item 6")
            add("Item 7")
            add("Item 8")
        }

        binding.listView.adapter = BaseAdapterImpl(array)

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Item Clicked : " + array[position], Toast.LENGTH_SHORT).show()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(
                    applicationContext,
                    "Permission request granted",
                    Toast.LENGTH_LONG
                ).show()
                setupCamera()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Permission request denied",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    private fun setupCamera() {
        //binding.fragmentContainer = cameraFragment
        transition.add(binding.fragmentContainer.id, cameraFragment)
        transition.commit()

    }
}