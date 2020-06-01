package com.lambda.spark;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HorizontalProductScrollAdapter.OnNoteListener {


    public HomeFragment() {
        // Required empty public constructor
    }

//    /////////////////////////////////////// Categories RecyclerView
//       private RecyclerView categoryRecyclerView;
//        private CategoryAdapter categoryAdapter;

    ////////////////////////////////////// banner slider code

    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage =2;
    private Timer timer;
    private List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
    final private long DELAY_TIME= 3000;
    final private long PERIOD_TIME = 3000;

    ///////////////////////////////////// banner slider code

    ///////////////////////////////////////// Horizontal product slider

    private TextView horizontalLayoutTitle;
    private Button horizontalViewAllBtn;
    private RecyclerView horizontalRecyclerView;

    ///////////////////////////////////////// Horizontal product slider

    ///////////////////////////////////////// Recommendation products for home page
    private TextView gridlayoutTitle;
    private Button gridLayoutViewAllBtn;
    private GridView gridView;

    ///////////////////////////////////////// Recommendation products for home page

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       ///////////////////////////Category Banner at Top of home page

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        categoryRecyclerView.setLayoutManager(layoutManager);
//
//        List<CategoryModel> categoryModelList= new ArrayList<CategoryModel>();
//        categoryModelList.add(new CategoryModel("link", "Home"));
//        categoryModelList.add(new CategoryModel("link", "Electronics"));
//        categoryModelList.add(new CategoryModel("link", "Appliances"));
//        categoryModelList.add(new CategoryModel("link", "Toys"));
//        categoryModelList.add(new CategoryModel("link", "Shoes"));
//        categoryModelList.add(new CategoryModel("link", "Furniture"));
//        categoryModelList.add(new CategoryModel("link", "Books"));
//        categoryModelList.add(new CategoryModel("link", "Sports"));
//
//            categoryAdapter = new CategoryAdapter(categoryModelList);
//            categoryRecyclerView.setAdapter(categoryAdapter);
//            categoryAdapter.notifyDataSetChanged();

        ///////////////////////////Category Banner at Top of home page


        ///////////////////////////Banner slider code

        bannerSliderViewPager = view.findViewById(R.id.banner_viewpager);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.carousel_img3));
        sliderModelList.add(new SliderModel(R.drawable.carousel_img4));

        // OG list
        sliderModelList.add(new SliderModel(R.drawable.carousel_img1));
        sliderModelList.add(new SliderModel(R.drawable.carousel_img2));
        sliderModelList.add(new SliderModel(R.drawable.carousel_img3));
        sliderModelList.add(new SliderModel(R.drawable.carousel_img4));
        //OG list//

        sliderModelList.add(new SliderModel(R.drawable.carousel_img1));
        sliderModelList.add(new SliderModel(R.drawable.carousel_img2));


        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);

        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {

                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        bannerSliderShow(); //start slideshow

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideshow();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    bannerSliderShow();
                }
                return false;
            }
        });
        /////////////////////////////Banner Slider code

        ////////////////////////////horizontal product layout code

        horizontalLayoutTitle = view.findViewById(R.id.howizontal_product_title);
        horizontalViewAllBtn = view.findViewById(R.id.horizontal_view_all_button);
        horizontalRecyclerView= view.findViewById(R.id.horizontal_product_recyclerview);


        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img1, "Organic Strawberries", "$30","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img2, "Limes", "$10","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img3, "Organic Hass Avocado","$30","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img10, "Milk", "$5","Dairy"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img11, "Butter","$2","Dairy"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img12, "Turkey", "$49","Meat"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img4, "Bag of Organic Bananas", "$15","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img9, "Lime Soda", "$8","Beverage"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img5, "Organic Garlic", "$5","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img6, "Organic Baby Spinach", "$10","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img7, "Banana", "$10","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img8, "Organic Blueberries", "$25","Grocery"));
        Log.e("fathersaab", String.valueOf(horizontalProductModelList.get(0).getProductImg()));
        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductModelList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        //horizontalRecyclerView.onItemClickListener
        horizontalProductScrollAdapter.notifyDataSetChanged();

        ////////////////////////////horizontal product layout code


        ////////////////////////////reco product code

        gridlayoutTitle = view.findViewById(R.id.reco_title_tv);
        gridLayoutViewAllBtn = view.findViewById(R.id.reco_viewAll_btn);
        gridView = view.findViewById(R.id.reco_gridview_layout);

        List<HorizontalProductModel> recoItemModelList = new ArrayList<>();

        recoItemModelList.add(new HorizontalProductModel(R.drawable.product_img1, "Comfy Bedding", "$40","Grocery"));
        recoItemModelList.add(new HorizontalProductModel(R.drawable.product_img2, "Fashion Kurtas", "$30","Grocery"));
        recoItemModelList.add(new HorizontalProductModel(R.drawable.product_img3, "Microwave Ovens","$190","Grocery"));
        recoItemModelList.add(new HorizontalProductModel(R.drawable.product_img4, "Latest Shoes", "$45","Grocery"));
        recoItemModelList.add(new HorizontalProductModel(R.drawable.scan_reco_img4, "Homely Couch", "$250","Grocery"));
        recoItemModelList.add(new HorizontalProductModel(R.drawable.product_img5, "BoAt Speakers", "$149","Grocery"));
        recoItemModelList.add(new HorizontalProductModel(R.drawable.product_img6, "Chanel Parfum", "$119","Grocery"));
        recoItemModelList.add(new HorizontalProductModel(R.drawable.product_img7, "Vegan Milk", "$25","Grocery"));

        gridView.setAdapter(new RecoItemAdapter(recoItemModelList));



        ////////////////////////////reco product code

        return view;

    }

    //to make infinite scroll for banner slider

    private void pageLooper(){
        //index = 7, list size =6+1= 7

        if(currentPage == sliderModelList.size() - 2 ){

            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false );
                //animation is false
        }
        if(currentPage == 1){

            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage, false );

        }
    }


    //for auto slideshow
    private void  bannerSliderShow(){
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(currentPage >= sliderModelList.size()){
                    currentPage= 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSlideshow(){
        timer.cancel();
    }

    @Override
    public void onNoteClick(int position) {

        Intent homeIntent= new Intent(this.getContext(), ProductPop.class);
        if(position % 4 == 0){
            homeIntent.putExtra("Status","Out of Stock");

        }
        else {
            homeIntent.putExtra("Status","In Stock");
        }
        homeIntent.putExtra("Image", String.valueOf(horizontalProductModelList.get(position).getProductImg()));
        homeIntent.putParcelableArrayListExtra("full_object", horizontalProductModelList.get(position));
        homeIntent.putExtra("Title",String.valueOf(horizontalProductModelList.get(position).getProductTitle()));
        homeIntent.putExtra("Price",String.valueOf(horizontalProductModelList.get(position).getProductPrice()));
        homeIntent.putExtra("ID",position);
        homeIntent.putExtra("Section", horizontalProductModelList.get(position).getProductDept());


        startActivity(homeIntent);


    }
}
