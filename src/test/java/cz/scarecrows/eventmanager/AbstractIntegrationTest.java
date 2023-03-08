/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * AbstractServiceTest
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AbstractIntegrationTest { }
