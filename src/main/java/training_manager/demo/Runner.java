package training_manager.demo;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.demo.dto.UserDTO;
import training_manager.demo.entity.User;
import training_manager.demo.entity.Weight;
import training_manager.demo.enums.TrainingTypeEnum;
import training_manager.demo.repository.TrainingDayRepository;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.repository.WeightRepository;
import training_manager.demo.service.mapper.MapperDTO;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class Runner {

    @Autowired
    UserRepository repository;

    @PostConstruct
    @Transactional
    public void test() {
        User mask = repository.findByNickname("mask").get();
        System.out.println(mask);
        UserDTO ilon = new UserDTO(5l, null, 50, TrainingTypeEnum.PRO);
        new MapperDTO<User, UserDTO>().toEntity(mask, ilon);
        System.out.println(mask);
        ModelMapper modelMapper = new ModelMapper();
        User mask1 = repository.findByNickname("mask").get();
        UserDTO map = modelMapper.map(mask1, ilon.getClass());
        System.out.println(map);
           System.out.println(repository.findByNickname("mask").get().getBodyMeasurements());
         System.out.println(mask.getBodyMeasurements());
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
        TrainingDayRepository bean = context.getBean(TrainingDayRepository.class);
        WeightRepository weightRepository = context.getBean(WeightRepository.class);
        //user = new User("mask", "lazy");
        //weightRepository.save(new Weight(20, user));
        Weight weight = weightRepository.findByUserIdAndDate(2l, LocalDate.of(2022, 2, 23)).get();
        System.out.println(weight);
    }

}
