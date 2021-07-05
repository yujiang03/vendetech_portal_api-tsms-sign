package vendetech_portal;

import com.vendetech.VendetechApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {VendetechApplication.class})
@ActiveProfiles("dev")
public class ScheduleTaskTest {

    // @Autowired
    // private UserService userService;
	//
	// @Test
	// public void updateSysUserByTask() throws ApiException {
	// 	userService.updateSysUserByTask();
	// }



}
