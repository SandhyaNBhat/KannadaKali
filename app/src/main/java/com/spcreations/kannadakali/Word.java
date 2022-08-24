package com.spcreations.kannadakali;

/**
 *Word represents a vocabulary word that the user wants to learn. It contains a default translation
 * and a Kannada translation for that word
 */

public class Word {

    /*Default translation for the word*/
    private String mDefaultTranslation;

    /*Kannada translation for the word*/
    private String mKannadaTranslation;

    /** Audio resource ID for the word */
    private int mAudioResourceId =NO_AUDIO_PROVIDED;

    /*Resource id of the image*/
    private int mImageId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    private static final int NO_AUDIO_PROVIDED = -1;



    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param kannadaTranslation is the word in the kannada language
     * @param imageId is the resource id of the image
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */

    public Word(String defaultTranslation, String kannadaTranslation, int imageId,int audioResourceId) {

        mDefaultTranslation = defaultTranslation;
        mKannadaTranslation = kannadaTranslation;
        mImageId = imageId;
        mAudioResourceId = audioResourceId;

    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param kannadaTranslation is the word in the kannada language
     *
     */

    public Word(String defaultTranslation, String kannadaTranslation, int audioResourceId) {

        mDefaultTranslation = defaultTranslation;
        mKannadaTranslation = kannadaTranslation;
        mAudioResourceId = audioResourceId;

    }


    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Kannada translation of the word.
     */
    public String getKannadaTranslation() {
        return mKannadaTranslation;
    }

    /**
     * Get the resource id of the image
     */
    public int getImageResourceId() {
        return mImageId;
    }

    /*
     * Check if the class has image resource id, if yes return true else return false
     */
    public boolean hasImage() {
        return mImageId  != NO_IMAGE_PROVIDED;
    }

    //

    /*
     * Check if the class has audio resource id, if yes return true else return false
     */
    public boolean hasAudio() {
        return mAudioResourceId  != NO_AUDIO_PROVIDED;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mKannadaTranslation='" + mKannadaTranslation + '\'' +
                ", mAudioResourceId=" + mAudioResourceId +
                ", mImageId=" + mImageId +
                '}';
    }
}
