public class ActionParser {
    public static Action[] parse(String[] arr){
        Action[] res = new Action[arr.length];

        int i = 0;

        for(String actStr : arr){
            System.out.println(actStr);
            switch (actStr){
                case "d-" : res[i] = Action.DAY_EARLIER; i+=1;continue;

                case "d+" : res[i] = Action.DAY_LATER; i += 1;continue;

                case "t-" : res[i] = Action.TIME_EARLIER; i+=1;continue;
                case "t+" : res[i] = Action.TIME_LATER; i +=1;continue;
                default:
                    throw new IllegalArgumentException("Translation " + actStr + " is incorrect");
            }


        }
        return res;
    }
}
