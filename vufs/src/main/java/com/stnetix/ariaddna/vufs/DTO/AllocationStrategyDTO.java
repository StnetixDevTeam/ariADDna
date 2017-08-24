package com.stnetix.ariaddna.vufs.DTO;

/**
 * Describes the types of allocation strategy.
 */
public enum AllocationStrategyDTO {

    /** Each file has one copy*/
    UNION,
    /** Each file has 2 or more copy*/
    MIRROR,
    /** Each file has 3 or more copy*/
    HIGH_AVAILABILITY
}
