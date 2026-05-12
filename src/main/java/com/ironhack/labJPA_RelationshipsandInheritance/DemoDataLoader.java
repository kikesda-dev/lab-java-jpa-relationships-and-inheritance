package com.ironhack.labJPA_RelationshipsandInheritance;

import com.ironhack.labJPA_RelationshipsandInheritance.model.association.*;
import com.ironhack.labJPA_RelationshipsandInheritance.model.contact.Contact;
import com.ironhack.labJPA_RelationshipsandInheritance.model.contact.Name;
import com.ironhack.labJPA_RelationshipsandInheritance.model.event.*;
import com.ironhack.labJPA_RelationshipsandInheritance.model.task.BillableTask;
import com.ironhack.labJPA_RelationshipsandInheritance.model.task.InternalTask;
import com.ironhack.labJPA_RelationshipsandInheritance.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DemoDataLoader implements CommandLineRunner {
    private final ContactRepository contactRepository;
    private final AssociationRepository associationRepository;
    private final DivisionRepository divisionRepository;
    private final MemberRepository memberRepository;
    private final SpeakerRepository speakerRepository;
    private final GuestRepository guestRepository;
    private final ConferenceRepository conferenceRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final BillableTaskRepository billableTaskRepository;
    private final InternalTaskRepository internalTaskRepository;

    public DemoDataLoader(ContactRepository contactRepository,
                          AssociationRepository associationRepository,
                          DivisionRepository divisionRepository,
                          MemberRepository memberRepository,
                          SpeakerRepository speakerRepository,
                          GuestRepository guestRepository,
                          ConferenceRepository conferenceRepository,
                          ExhibitionRepository exhibitionRepository,
                          BillableTaskRepository billableTaskRepository,
                          InternalTaskRepository internalTaskRepository) {
        this.contactRepository = contactRepository;
        this.associationRepository = associationRepository;
        this.divisionRepository = divisionRepository;
        this.memberRepository = memberRepository;
        this.speakerRepository = speakerRepository;
        this.guestRepository = guestRepository;
        this.conferenceRepository = conferenceRepository;
        this.exhibitionRepository = exhibitionRepository;
        this.billableTaskRepository = billableTaskRepository;
        this.internalTaskRepository = internalTaskRepository;
    }

    @Override
    public void run(String... args) {
        if (contactRepository.count() > 0) return;

        loadContacts();
        loadAssociationWithDivisions();
        loadEventsAndGuests();
        loadTasks();
    }

    private void loadContacts() {
        contactRepository.save(new Contact(null, "TechCorp", "CEO",
                new Name("Mr.", "John", "Michael", "Smith")));
        contactRepository.save(new Contact(null, "HealthInc", "CTO",
                new Name("Dr.", "Sarah", "Anne", "Johnson")));
        contactRepository.save(new Contact(null, "EduWorld", "Director",
                new Name("Mrs.", "Emily", "Rose", "Davis")));
        contactRepository.save(new Contact(null, "FinanceHub", "Manager",
                new Name("Mr.", "David", "James", "Brown")));
    }

    private void loadAssociationWithDivisions() {
        Association association = associationRepository.save(
                new Association(null, "Nurse Association of Spain", new ArrayList<>()));

        String[] districts = {"Madrid", "Barcelona", "Valencia", "Seville", "Bilbao", "Malaga", "Zaragoza"};
        String[] divisionNames = {"Emergency Care", "Pediatrics", "Surgery", "Cardiology",
                "Oncology", "Neurology", "Geriatrics"};

        for (int i = 0; i < 7; i++) {
            Division division = divisionRepository.save(
                    new Division(null, divisionNames[i], districts[i], null, null, association));

            Member president = memberRepository.save(
                    new Member(null, "President " + divisionNames[i], MemberStatus.ACTIVE,
                            LocalDate.now().plusYears(1), division));

            Member member = memberRepository.save(
                    new Member(null, "Member " + divisionNames[i],
                            i % 2 == 0 ? MemberStatus.ACTIVE : MemberStatus.LAPSED,
                            LocalDate.now().plusMonths(6), division));

            division.setPresident(president);
            division.setMembers(List.of(president, member));
            divisionRepository.save(division);
        }
    }

    private void loadEventsAndGuests() {
        Speaker speaker1 = speakerRepository.save(new Speaker(null, "Dr. Ada Lopez", 60));
        Speaker speaker2 = speakerRepository.save(new Speaker(null, "Prof. Bob Chen", 45));
        Speaker speaker3 = speakerRepository.save(new Speaker(null, "Ms. Carol White", 30));

        Conference conf = new Conference();
        conf.setTitle("AI in Healthcare");
        conf.setDate(LocalDate.of(2026, 6, 15));
        conf.setDuration(120);
        conf.setLocation("Madrid Convention Center");
        conf.setSpeakers(List.of(speaker1, speaker2));
        conf = conferenceRepository.save(conf);

        Exhibition exh = new Exhibition();
        exh.setTitle("Medical Tech Expo 2026");
        exh.setDate(LocalDate.of(2026, 7, 1));
        exh.setDuration(480);
        exh.setLocation("Barcelona Fairgrounds");
        exh = exhibitionRepository.save(exh);

        Conference conf2 = new Conference();
        conf2.setTitle("Nursing Innovation Summit");
        conf2.setDate(LocalDate.of(2026, 8, 20));
        conf2.setDuration(90);
        conf2.setLocation("Valencia Congress Hall");
        conf2.setSpeakers(List.of(speaker3));
        conf2 = conferenceRepository.save(conf2);

        guestRepository.save(new Guest(null, "Maria Garcia", GuestStatus.ATTENDING, conf));
        guestRepository.save(new Guest(null, "Jose Martinez", GuestStatus.NOT_ATTENDING, conf));
        guestRepository.save(new Guest(null, "Anna Torres", GuestStatus.NO_RESPONSE, exh));
        guestRepository.save(new Guest(null, "Luis Fernandez", GuestStatus.ATTENDING, exh));
        guestRepository.save(new Guest(null, "Carla Ruiz", GuestStatus.ATTENDING, conf2));
        guestRepository.save(new Guest(null, "Pedro Sanchez", GuestStatus.NOT_ATTENDING, conf2));
    }

    private void loadTasks() {
        BillableTask billable1 = new BillableTask();
        billable1.setTitle("Develop billing module");
        billable1.setDueDate(LocalDate.of(2026, 6, 30));
        billable1.setCompleted(false);
        billable1.setHourlyRate(new BigDecimal("150.00"));
        billableTaskRepository.save(billable1);

        BillableTask billable2 = new BillableTask();
        billable2.setTitle("Consulting hours");
        billable2.setDueDate(LocalDate.of(2026, 5, 15));
        billable2.setCompleted(true);
        billable2.setHourlyRate(new BigDecimal("200.00"));
        billableTaskRepository.save(billable2);

        InternalTask internal1 = new InternalTask();
        internal1.setTitle("Team meeting notes");
        internal1.setDueDate(LocalDate.of(2026, 4, 10));
        internal1.setCompleted(true);
        internalTaskRepository.save(internal1);

        InternalTask internal2 = new InternalTask();
        internal2.setTitle("Code review");
        internal2.setDueDate(LocalDate.of(2026, 7, 5));
        internal2.setCompleted(false);
        internalTaskRepository.save(internal2);
    }
}