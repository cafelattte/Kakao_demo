package com.example.latte.kakao_demo.widget;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.latte.kakao_demo.GlobalApplication;
import com.example.latte.kakao_demo.R;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.AgeRange;
import com.kakao.usermgmt.response.model.Gender;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.OptionalBoolean;

public class ProfileLayout extends FrameLayout {
    private MeV2ResponseCallback meV2ResponseCallback;

    private String email;
    private String phoneNumber;
    private String nickname;
    private String userId;
    private String birthDay;
    private String ageRange;
    private String gender;
    private String countryIso;
    private NetworkImageView profile;
    private NetworkImageView background;
    private TextView profileDescription;

    public ProfileLayout(Context context) {
        super(context);
        initView();
    }

    public ProfileLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ProfileLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    // set callback from userinfo request response
    public void setMeV2ResponseCallback(final MeV2ResponseCallback callback) {
        this.meV2ResponseCallback = callback;
    }

    // update user profile view
    public void setUserProfile(final UserProfile userProfile) {
        setEmail(userProfile.getEmail());
        setProfileURL(userProfile.getProfileImagePath());
        setNickname(userProfile.getNickname());
        setUserId(String.valueOf(userProfile.getId()));
    }

    public void setUserInfo(final MeV2Response response) {
        if (response.getKakaoAccount().hasEmail() == OptionalBoolean.TRUE && response.getKakaoAccount().getEmail() == null) {
            setEmail(getContext().getString(R.string.needs_account_email_scope));
        } else {
            setEmail(response.getKakaoAccount().getEmail());
        }
        if (response.getKakaoAccount().hasPhoneNumber() == OptionalBoolean.TRUE && response.getKakaoAccount().getPhoneNumber() == null) {
            setPhoneNumber(getContext().getString(R.string.needs_phone_number_scope));
        } else {
            setPhoneNumber(response.getKakaoAccount().getPhoneNumber());
        }
        if (response.getKakaoAccount().getBirthday() != null) {
            setBirthDay(response.getKakaoAccount().getBirthday());
        }
        if (response.getProperties() != null && response.getProperties().containsKey(MeV2Response.KEY_PROFILE_IMAGE)) {
            setProfileURL(response.getProperties().get(MeV2Response.KEY_PROFILE_IMAGE));
        }
        if (response.getKakaoAccount().getAgeRange() != null) {
            setAgeRange(response.getKakaoAccount().getAgeRange());
        }
        if (response.getKakaoAccount().getGender() != null) {
            setGender(response.getKakaoAccount().getGender());
        }

        if (response.getProperties() != null) {
            setNickname(response.getProperties().get(MeV2Response.KEY_NICKNAME));
        }
        setUserId(String.valueOf(response.getId()));
        updateLayout();
    }

    public void setEmail(final String email) {
        this.email = email;
        updateLayout();
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
        updateLayout();
    }

    // update profile image view
    public void setProfileURL(final String profileImageURL) {
        if (profile != null && profileImageURL != null) {
            Application app = GlobalApplication.getGlobalApplicationContext();
            if (app == null)
                throw new UnsupportedOperationException("needs com.kakao.GlobalApplication in order to use ImageLoader");
            profile.setImageUrl(profileImageURL, ((GlobalApplication) app).getImageLoader());
        }
    }

    public void setBgImageURL(String bgImageURL) {
        if (bgImageURL != null) {
            Application app = GlobalApplication.getGlobalApplicationContext();
            if (app == null)
                throw new UnsupportedOperationException("needs com.kakao.GlobalApplication in order to use ImageLoader");
            background.setImageUrl(bgImageURL, ((GlobalApplication) app).getImageLoader());
        }
    }

    public void setDefaultBgImage(int imageResId) {
        if (background != null) {
            background.setBackgroundResource(imageResId);
        }
    }

    public void setDefaultProfileImage(int imageResId) {
        if (profile != null) {
            profile.setBackgroundResource(imageResId);
        }
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso;
        updateLayout();
    }

    // update nickname view
    public void setNickname(final String nickname) {
        this.nickname = nickname;
        updateLayout();
    }

    public void setBirthDay(final String birthDay) {
        this.birthDay = birthDay;
        updateLayout();
    }

    public void setAgeRange(AgeRange ageRange) {
        if (ageRange != null) {
            this.ageRange = ageRange.getValue();
        }
    }

    public void setGender(Gender gender) {
        if (gender != null) {
            this.gender = gender.getValue();
        }
    }

    public void setBackground(NetworkImageView background) {
        this.background = background;
    }

    // update user id view
    public void setUserId(final String userId) {
        this.userId = userId;
        updateLayout();
    }

    private void updateLayout() {
        StringBuilder builder = new StringBuilder();

        if (!TextUtils.isEmpty(email)) {
            builder.append(getResources().getString(R.string.com_kakao_profile_email)).append('\n').append(email).append('\n');
        }
        if (!TextUtils.isEmpty(phoneNumber)) {
            builder.append(getResources().getString(R.string.com_kakao_profile_phone_number)).append('\n').append(phoneNumber).append('\n');
        }
        if (nickname != null && nickname.length() > 0) {
            builder.append(getResources().getString(R.string.com_kakao_profile_nickname)).append("\n").append(nickname).append("\n");
        }

        if (userId != null && userId.length() > 0) {
            builder.append(getResources().getString(R.string.com_kakao_profile_userId)).append("\n").append(userId).append("\n");
        }
        if (gender != null) {
            builder.append(getResources().getString(R.string.com_kakao_profile_gender)).append(" ").append(gender).append("\n");
        }
        if (ageRange != null) {
            builder.append(getResources().getString(R.string.com_kakao_profile_age_range)).append(" ").append(ageRange).append("\n");
        }
        if (birthDay != null && birthDay.length() > 0) {
            builder.append(getResources().getString(R.string.com_kakao_profile_birthday)).append(" ").append(birthDay);
        }
        if (countryIso != null) {
            builder.append(getResources().getString(R.string.kakaotalk_country_label)).append("\n").append(countryIso);
        }
        if (profileDescription != null) {
            profileDescription.setText(builder.toString());
        }
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.layout_common_kakao_profile, this);

        profile = view.findViewById(R.id.com_kakao_profile_image);
        background = view.findViewById(R.id.background);
        profileDescription = view.findViewById(R.id.profile_description);
    }

    // request user info
    public void requestMe() {
        UserManagement.getInstance().me(meV2ResponseCallback);
    }
}
