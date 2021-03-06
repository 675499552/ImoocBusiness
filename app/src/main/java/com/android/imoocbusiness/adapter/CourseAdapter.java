package com.android.imoocbusiness.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.imoocbusiness.R;
import com.android.imoocbusiness.module.recommand.RecommandBodyValue;
import com.android.imoocbusiness.util.Util;
import com.android.jyc.adutil.Utils;
import com.android.jyc.imageloader.ImageLoaderManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 2017/2/21.
 *
 * @Function
 */

public class CourseAdapter extends BaseAdapter {

    /**
     * ListView不同类型的Item标识
     */
    private static final int CARD_COUNT = 4;
    private static final int VIDOE_TYPE = 0x00;
    private static final int CARD_TYPE_ONE = 0x01;
    private static final int CARD_TYPE_TWO = 0x02;
    private static final int CARD_TYPE_THREE = 0x03;


    private LayoutInflater mInflate;
    private ViewHolder mViewHolder;
    private Context mContext;

    private ArrayList<RecommandBodyValue> mData;
    /**
     * 异步图片加载工具类
     */
    private ImageLoaderManager mImagerLoader;

    public CourseAdapter(Context mContext, ArrayList<RecommandBodyValue> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflate = LayoutInflater.from(mContext);
        mImagerLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return CARD_COUNT;
    }

    /**
     * 通知Adapter 使用那种类型的Item去加载数据
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        return value.type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1.获取数据的type类型
        int type = getItemViewType(position);
        final RecommandBodyValue value = (RecommandBodyValue) getItem(position);

        //为空表明当前没有使用的缓存View
        if (convertView == null) {
            switch (type) {
                case VIDOE_TYPE:
                    //显示video卡片
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_video_layout, parent, false);
                    mViewHolder.mVieoContentLayout = (RelativeLayout)
                            convertView.findViewById(R.id.video_ad_layout);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mShareView = (ImageView) convertView.findViewById(R.id.item_share_view);
                    //为对应布局创建播放器
//                mAdsdkContext = new VideoAdContext(mViewHolder.mVieoContentLayout,
//                        new Gson().toJson(value), null);
//                mAdsdkContext.setAdResultListener(new AdContextInterface() {
//                    @Override
//                    public void onAdSuccess() {
//                    }
//
//                    @Override
//                    public void onAdFailed() {
//                    }
//
//                    @Override
//                    public void onClickVideo(String url) {
//                        Intent intent = new Intent(mContext, AdBrowserActivity.class);
//                        intent.putExtra(AdBrowserActivity.KEY_URL, url);
//                        mContext.startActivity(intent);
//                    }
//                });
                    break;
                case CARD_TYPE_ONE:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_one_layout, parent, false);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = (TextView) convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = (TextView) convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = (TextView) convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductLayout = (LinearLayout) convertView.findViewById(R.id.product_photo_layout);
                    break;
                case CARD_TYPE_TWO:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_two_layout, parent, false);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mProductView = (ImageView) convertView.findViewById(R.id.product_photo_view);
                    mViewHolder.mPriceView = (TextView) convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = (TextView) convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = (TextView) convertView.findViewById(R.id.item_zan_view);
                    break;
                case CARD_TYPE_THREE:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_three_layout, null, false);
                    mViewHolder.mViewPager = (ViewPager) convertView.findViewById(R.id.pager);

                    mViewHolder.mViewPager.setPageMargin(Utils.dip2px(mContext,12));
                    //为ViewPage填充数据
                    ArrayList<RecommandBodyValue> recommandBodyValues = Util.handleData(value);
                    mViewHolder.mViewPager.setAdapter(new HotSalePagerAdapter(mContext,recommandBodyValues));
                    //一开始就让ViewPager处于中间的位置  这样就可以左右滑动了

                    mViewHolder.mViewPager.setCurrentItem(recommandBodyValues.size()*100);
                    break;
            }
            convertView.setTag(mViewHolder);
        }
        //ConverView
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        //开始绑定数据
        switch (type) {
            case CARD_TYPE_ONE:
                //多图Iiem绑定数据
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));

                /**
                 * logoView加载异步图片
                 */
                mImagerLoader.displayImage(mViewHolder.mLogoView, value.logo);
                //动态的添加ImageView到水平ScrollView中
                mViewHolder.mProductLayout.removeAllViews();//删除已有的图片
                for (String url : value.url) {
                    mViewHolder.mProductLayout.addView(createImageView(url));
                }

                break;
            case CARD_TYPE_TWO:
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));
                /**
                 * 为ImageView完成图片的加载
                 */
                mImagerLoader.displayImage(mViewHolder.mLogoView, value.logo);
                mImagerLoader.displayImage(mViewHolder.mProductView, value.url.get(0));
                break;
        }
        return convertView;
    }

    /**
     * 动态的创建ImageView
     *
     * @return
     */
    private ImageView createImageView(String url) {

        ImageView imageView = new ImageView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Utils.dip2px(mContext, 100), LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.leftMargin = Utils.dip2px(mContext, 5);
        imageView.setLayoutParams(params);
        mImagerLoader.displayImage(imageView, url);
        return imageView;
    }

    /**
     * 用来缓存我们已经创建好的Item
     */
    private static class ViewHolder {
        //所有Card共有属性
        private CircleImageView mLogoView;
        private TextView mTitleView;
        private TextView mInfoView;
        private TextView mFooterView;
        //Video Card特有属性
        private RelativeLayout mVieoContentLayout;
        private ImageView mShareView;

        //Video Card外所有Card具有属性
        private TextView mPriceView;
        private TextView mFromView;
        private TextView mZanView;
        //Card One特有属性
        private LinearLayout mProductLayout;
        //Card Two特有属性
        private ImageView mProductView;
        //Card Three特有属性
        private ViewPager mViewPager;
    }
}
