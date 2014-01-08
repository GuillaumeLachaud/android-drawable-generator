package com.gla.gradle.adg.tasks;

import com.gla.gradle.adg.Density;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.im4java.core.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by guillaumelachaud on 1/7/14.
 */
public class ResizeRegularDrawableTask  extends DefaultTask {

    private Density mMinDensity;
    private Density mRefDensity;

    @TaskAction
    public void run(){

        mMinDensity = (Density) getProject().getExtensions().getExtraProperties().get(DirectoriesSetupTask.MIN_DENSITY_PROPERTY);
        mRefDensity = (Density) getProject().getExtensions().getExtraProperties().get(DirectoriesSetupTask.REF_DENSITY_PROPERTY);

        String resDirectory = getProject().getProjectDir().getAbsolutePath() + "/src/main/res/";

        List<String> sourceImages = new LinkedList<String>(Arrays.asList(new File(resDirectory + mRefDensity.getFolder()).list()));

        removeNinePatchesFromList(sourceImages);

        System.out.println(String.format("Ref Density %s has %d files", mRefDensity, sourceImages.size()));

        for(int i = mMinDensity.ordinal() ; i < mRefDensity.ordinal() ; i++){
            Density currentDensity = Density.values()[i];
            List<String> densityImages = new LinkedList<String>(Arrays.asList(new File(resDirectory + currentDensity.getFolder()).list()));
            removeNinePatchesFromList(densityImages);
            System.out.println(String.format(" Density %s has %d files", currentDensity, densityImages.size()));
            removeUnInheritedFiled(densityImages, sourceImages);
            System.out.println(String.format(" %d files to generate", sourceImages.size() - densityImages.size()));

            ConvertCmd cmd = new ConvertCmd();
            IMOperation op = new IMOperation();
            op.addImage();
            op.comment("adg generated");
            op.addDynamicOperation(new DynamicResizeOperation(mRefDensity, currentDensity));
            op.addImage();

            for(int j = 0 ; j < sourceImages.size() ; j++){
                try {
                    cmd.run(op,resDirectory + mRefDensity.getFolder() + sourceImages.get(j),resDirectory + currentDensity.getFolder() + sourceImages.get(j));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IM4JavaException e) {
                    e.printStackTrace();
                }
            }


        }


    }

    private void removeNinePatchesFromList(List<String> list){
        Iterator<String> ite = list.iterator();
        while(ite.hasNext()){
            String fileName = ite.next();
            if(fileName.endsWith(".9.png") || !fileName.endsWith(".png")){
                //This is a 9-patch, skip for now
                ite.remove();
            }
        }
    }

    private void removeUnInheritedFiled(List<String> densityImages, List<String> sourceImages) {
        //This method will remove files that are present in sub-densities but not in the reference one.
        //We don't want to modify these files

        Iterator<String> ite = densityImages.iterator();
        while (ite.hasNext()){
            if(!sourceImages.contains(ite.next())){
                ite.remove();
            }
        }

    }

    class DynamicResizeOperation implements DynamicOperation {

        private int percentage;

        public DynamicResizeOperation(Density refDensity, Density currentDensity) {
            percentage = (int) (((float) currentDensity.getValue() / (float) refDensity.getValue()) * 100);
        }


        @Override
        public Operation resolveOperation(Object... pImages) throws IM4JavaException {
            System.out.println(pImages);
            //return the resize operation if and only if both height and width are non zero positive values
            IMOperation op = new IMOperation();
            op.resize(percentage, percentage, "%");
            return op;
        }
    }

}
