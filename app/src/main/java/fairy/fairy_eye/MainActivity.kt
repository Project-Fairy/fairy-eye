package fairy.fairy_eye

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import fairy.fairy_eye.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val cameraFragment = CameraFragment()
    private val cameraDeniedFragment = CameraDeniedFragment()
    private val fragmentManager: FragmentManager = supportFragmentManager
    private var transition: FragmentTransaction = fragmentManager.beginTransaction()

    private val viewModel: MainViewModel by viewModels()

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

        transition.add(binding.fragmentContainer.id, cameraDeniedFragment)
        transition.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * This method is called when any item is clicked from the menu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.setting -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.gemini -> {
                Toast.makeText(this, "Gemini Clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(
                    applicationContext,
                    "Permission request granted",
                    Toast.LENGTH_LONG
                ).show()
                transition = fragmentManager.beginTransaction()
                transition.replace(binding.fragmentContainer.id, cameraFragment).commit()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Permission request denied",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

}