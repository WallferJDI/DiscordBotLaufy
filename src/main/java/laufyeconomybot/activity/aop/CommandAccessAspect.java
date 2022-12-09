package laufyeconomybot.activity.aop;

//@Component
//@Aspect
public class CommandAccessAspect {


//    @Pointcut("within(com.wallferjdi.laufybotwallfer.commands.slash_commands_admin.*)")
//    private void directory(){}
//
//    @Pointcut("execution(* request(..))")
//    private void method(){}
//
//    @Around("directory()&&method()")
//    public Object checkAdminPermissions(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object targetMethodResult=null;
//        Object[] args = joinPoint.getArgs();
//        for (Object obj:args) {
//            if(obj instanceof SlashCommandInteractionEvent){
//                SlashCommandInteractionEvent event = (SlashCommandInteractionEvent) obj;
//
//
//                if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
//                    targetMethodResult = joinPoint.proceed();
//                    return targetMethodResult;
//                }
//                event.reply(" U are not admin").queue();
//            }
//
//        }
//        return targetMethodResult;
//    }
}
