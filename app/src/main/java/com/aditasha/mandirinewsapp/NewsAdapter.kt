import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.aditasha.mandirinewsapp.R
import com.aditasha.mandirinewsapp.databinding.RecyclerItemBinding
import com.aditasha.mandirinewsapp.model.ArticlesItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var data = arrayListOf<ArticlesItem>()

    fun setData(articles: List<ArticlesItem>) {
        data = arrayListOf<ArticlesItem>().apply { addAll(articles) }
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
    class ViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())
        var output = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        fun bind(item: ArticlesItem) {
            var date: Date?
            var dateString = "-"
            item.publishedAt.let {
                date = it?.let { published -> input.parse(published) }
            }
            date?.let {
                dateString = output.format(it)
            }
            val request = ImageRequest.Builder(binding.headerImage.context)
                .data(item.urlToImage)
                .crossfade(true)
                .fallback(R.drawable.bmri_light)
                .target(
                    onStart = { _ ->
                        binding.headerImage.setImageDrawable(null)
                        binding.loading.visibility = View.VISIBLE
                    },
                    onSuccess = { drawable ->
                        binding.loading.visibility = View.INVISIBLE
                        binding.headerImage.setImageDrawable(drawable)
                    },
                    onError = { _ -> binding.loading.visibility = View.INVISIBLE })
                .build()

            val imageLoader = ImageLoader.Builder(binding.headerImage.context)
                .build()
            imageLoader.enqueue(request)
            binding.headlineNews.text = item.title
            binding.newsSource.text = item.source?.name
            binding.newsDate.text = dateString
        }
    }
}