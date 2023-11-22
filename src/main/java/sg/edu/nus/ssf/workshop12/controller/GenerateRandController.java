package sg.edu.nus.ssf.workshop12.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.ssf.workshop12.exception.RandNoException;
import sg.edu.nus.ssf.workshop12.model.Generate;

@Controller
@RequestMapping(path = "/rand")
public class GenerateRandController {

    @GetMapping(path="/show")
    public String showRandomForm(Model m){
        Generate g = new Generate();
        m.addAttribute("generateObj", g);
        return "generate";
    }

    @GetMapping(path = "/generate")
    public String generate(@RequestParam Integer numberVal, Model m)
    {
        this.randomizeNumber(m, numberVal.intValue());
        return "result";
    }

    private void randomizeNumber(Model m, 
                int noOfGenerateNo) 
    {
        int maxGenNo = 31;
        String[] imgNumbers = new String[maxGenNo];
        if(noOfGenerateNo < 1 || noOfGenerateNo > maxGenNo-1){
            throw new RandNoException();
        }

        for(int x = 0; x < maxGenNo; x++){
            if(x>0){
                System.out.print("x> " + x);
                imgNumbers[x] = "number" + x + ".jpg";
            }
        }

        List<String> selectedImgs = new ArrayList<String>();
        Random rand = new Random();
        Set<Integer> uniqueResult = new LinkedHashSet<Integer>();
        while(uniqueResult.size() < noOfGenerateNo){
            Integer randNoResult = rand.nextInt(maxGenNo);
            if(randNoResult != null){
                uniqueResult.add(randNoResult);
            }
        }

        Integer currElem = null;
        for(Iterator iter = uniqueResult.iterator(); iter.hasNext();){
            currElem = (Integer)iter.next();
            selectedImgs.add(imgNumbers[currElem.intValue()]);
        }

            m.addAttribute("numberRandomNum", noOfGenerateNo);
            m.addAttribute("randNumResult", selectedImgs.toArray());
            
        }
    }
