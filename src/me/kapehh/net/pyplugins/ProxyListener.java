package me.kapehh.net.pyplugins;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.hanging.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.painting.*;
import org.bukkit.event.player.*;
import org.bukkit.event.enchantment.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.server.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.*;
import org.bukkit.event.world.*;

/**
 * Created by karen on 26.09.2016.
 */
public class ProxyListener implements Listener {

    private void fireEvent(Event event) {
        System.out.println("Raized event: " + event.getEventName());
    }

    /*@EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockBurnEvent(BlockBurnEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockCanBuildEvent(BlockCanBuildEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockDamageEvent(BlockDamageEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockDispenseEvent(BlockDispenseEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockExpEvent(BlockExpEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockFadeEvent(BlockFadeEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockFormEvent(BlockFormEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockGrowEvent(BlockGrowEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockIgniteEvent(BlockIgniteEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockMultiPlaceEvent(BlockMultiPlaceEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockPistonEvent(BlockPistonEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockRedstoneEvent(BlockRedstoneEvent event) { fireEvent(event); }
    @EventHandler
    public void onBlockSpreadEvent(BlockSpreadEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityBlockFormEvent(EntityBlockFormEvent event) { fireEvent(event); }
    @EventHandler
    public void onLeavesDecayEvent(LeavesDecayEvent event) { fireEvent(event); }
    @EventHandler
    public void onNotePlayEvent(NotePlayEvent event) { fireEvent(event); }
    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event) { fireEvent(event); }
    @EventHandler
    public void onEnchantItemEvent(EnchantItemEvent event) { fireEvent(event); }
    @EventHandler
    public void onPrepareItemEnchantEvent(PrepareItemEnchantEvent event) { fireEvent(event); }
    @EventHandler
    public void onCreatureSpawnEvent(CreatureSpawnEvent event) { fireEvent(event); }
    @EventHandler
    public void onCreeperPowerEvent(CreeperPowerEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityBreakDoorEvent(EntityBreakDoorEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityChangeBlockEvent(EntityChangeBlockEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityCombustEvent(EntityCombustEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityCreatePortalEvent(EntityCreatePortalEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityInteractEvent(EntityInteractEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityPortalEnterEvent(EntityPortalEnterEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityPortalEvent(EntityPortalEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityPortalExitEvent(EntityPortalExitEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityTameEvent(EntityTameEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityTargetEvent(EntityTargetEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityTeleportEvent(EntityTeleportEvent event) { fireEvent(event); }
    @EventHandler
    public void onEntityUnleashEvent(EntityUnleashEvent event) { fireEvent(event); }
    @EventHandler
    public void onExpBottleEvent(ExpBottleEvent event) { fireEvent(event); }
    @EventHandler
    public void onExplosionPrimeEvent(ExplosionPrimeEvent event) { fireEvent(event); }
    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) { fireEvent(event); }
    @EventHandler
    public void onHorseJumpEvent(HorseJumpEvent event) { fireEvent(event); }
    @EventHandler
    public void onItemDespawnEvent(ItemDespawnEvent event) { fireEvent(event); }
    @EventHandler
    public void onItemSpawnEvent(ItemSpawnEvent event) { fireEvent(event); }
    @EventHandler
    public void onPigZapEvent(PigZapEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerLeashEntityEvent(PlayerLeashEntityEvent event) { fireEvent(event); }
    @EventHandler
    public void onPotionSplashEvent(PotionSplashEvent event) { fireEvent(event); }
    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event) { fireEvent(event); }
    @EventHandler
    public void onProjectileLaunchEvent(ProjectileLaunchEvent event) { fireEvent(event); }
    @EventHandler
    public void onSheepDyeWoolEvent(SheepDyeWoolEvent event) { fireEvent(event); }
    @EventHandler
    public void onSheepRegrowWoolEvent(SheepRegrowWoolEvent event) { fireEvent(event); }
    @EventHandler
    public void onSlimeSplitEvent(SlimeSplitEvent event) { fireEvent(event); }
    @EventHandler
    public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent event) { fireEvent(event); }
    @EventHandler
    public void onHangingBreakEvent(HangingBreakEvent event) { fireEvent(event); }
    @EventHandler
    public void onHangingPlaceEvent(HangingPlaceEvent event) { fireEvent(event); }
    @EventHandler
    public void onBrewEvent(BrewEvent event) { fireEvent(event); }
    @EventHandler
    public void onCraftItemEvent(CraftItemEvent event) { fireEvent(event); }
    @EventHandler
    public void onFurnaceBurnEvent(FurnaceBurnEvent event) { fireEvent(event); }
    @EventHandler
    public void onFurnaceExtractEvent(FurnaceExtractEvent event) { fireEvent(event); }
    @EventHandler
    public void onFurnaceSmeltEvent(FurnaceSmeltEvent event) { fireEvent(event); }
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) { fireEvent(event); }
    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) { fireEvent(event); }
    @EventHandler
    public void onInventoryCreativeEvent(InventoryCreativeEvent event) { fireEvent(event); }
    @EventHandler
    public void onInventoryDragEvent(InventoryDragEvent event) { fireEvent(event); }
    @EventHandler
    public void onInventoryEvent(InventoryEvent event) { fireEvent(event); }
    @EventHandler
    public void onInventoryInteractEvent(InventoryInteractEvent event) { fireEvent(event); }
    @EventHandler
    public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) { fireEvent(event); }
    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event) { fireEvent(event); }
    @EventHandler
    public void onInventoryPickupItemEvent(InventoryPickupItemEvent event) { fireEvent(event); }
    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent event) { fireEvent(event); }
    @EventHandler
    public void onPaintingBreakByEntityEvent(PaintingBreakByEntityEvent event) { fireEvent(event); }
    @EventHandler
    public void onPaintingBreakEvent(PaintingBreakEvent event) { fireEvent(event); }
    @EventHandler
    public void onPaintingPlaceEvent(PaintingPlaceEvent event) { fireEvent(event); }
    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) { fireEvent(event); }
    @EventHandler
    public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerAnimationEvent(PlayerAnimationEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerBucketEvent(PlayerBucketEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerBucketFillEvent(PlayerBucketFillEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerChannelEvent(PlayerChannelEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerChatEvent(PlayerChatEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerChatTabCompleteEvent(PlayerChatTabCompleteEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerEditBookEvent(PlayerEditBookEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerEggThrowEvent(PlayerEggThrowEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerExpChangeEvent(PlayerExpChangeEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerFishEvent(PlayerFishEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerInventoryEvent(PlayerInventoryEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerItemBreakEvent(PlayerItemBreakEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerPortalEvent(PlayerPortalEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerPreLoginEvent(PlayerPreLoginEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerRegisterChannelEvent(PlayerRegisterChannelEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerShearEntityEvent(PlayerShearEntityEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerTeleportEvent(PlayerTeleportEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerUnleashEntityEvent(PlayerUnleashEntityEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerUnregisterChannelEvent(PlayerUnregisterChannelEvent event) { fireEvent(event); }
    @EventHandler
    public void onPlayerVelocityEvent(PlayerVelocityEvent event) { fireEvent(event); }
    @EventHandler
    public void onMapInitializeEvent(MapInitializeEvent event) { fireEvent(event); }
    @EventHandler
    public void onPluginDisableEvent(PluginDisableEvent event) { fireEvent(event); }
    @EventHandler
    public void onPluginEnableEvent(PluginEnableEvent event) { fireEvent(event); }
    @EventHandler
    public void onPluginEvent(PluginEvent event) { fireEvent(event); }
    @EventHandler
    public void onRemoteServerCommandEvent(RemoteServerCommandEvent event) { fireEvent(event); }
    @EventHandler
    public void onServerCommandEvent(ServerCommandEvent event) { fireEvent(event); }
    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent event) { fireEvent(event); }
    @EventHandler
    public void onServiceEvent(ServiceEvent event) { fireEvent(event); }
    @EventHandler
    public void onServiceRegisterEvent(ServiceRegisterEvent event) { fireEvent(event); }
    @EventHandler
    public void onServiceUnregisterEvent(ServiceUnregisterEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleBlockCollisionEvent(VehicleBlockCollisionEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleCollisionEvent(VehicleCollisionEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleCreateEvent(VehicleCreateEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleDamageEvent(VehicleDamageEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleDestroyEvent(VehicleDestroyEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleEnterEvent(VehicleEnterEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleEntityCollisionEvent(VehicleEntityCollisionEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleExitEvent(VehicleExitEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleMoveEvent(VehicleMoveEvent event) { fireEvent(event); }
    @EventHandler
    public void onVehicleUpdateEvent(VehicleUpdateEvent event) { fireEvent(event); }
    @EventHandler
    public void onLightningStrikeEvent(LightningStrikeEvent event) { fireEvent(event); }
    @EventHandler
    public void onThunderChangeEvent(ThunderChangeEvent event) { fireEvent(event); }
    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) { fireEvent(event); }
    @EventHandler
    public void onWeatherEvent(WeatherEvent event) { fireEvent(event); }
    @EventHandler
    public void onChunkEvent(ChunkEvent event) { fireEvent(event); }
    @EventHandler
    public void onChunkLoadEvent(ChunkLoadEvent event) { fireEvent(event); }
    @EventHandler
    public void onChunkPopulateEvent(ChunkPopulateEvent event) { fireEvent(event); }
    @EventHandler
    public void onChunkUnloadEvent(ChunkUnloadEvent event) { fireEvent(event); }
    @EventHandler
    public void onPortalCreateEvent(PortalCreateEvent event) { fireEvent(event); }
    @EventHandler
    public void onSpawnChangeEvent(SpawnChangeEvent event) { fireEvent(event); }
    @EventHandler
    public void onStructureGrowEvent(StructureGrowEvent event) { fireEvent(event); }
    @EventHandler
    public void onWorldInitEvent(WorldInitEvent event) { fireEvent(event); }
    @EventHandler
    public void onWorldLoadEvent(WorldLoadEvent event) { fireEvent(event); }
    @EventHandler
    public void onWorldSaveEvent(WorldSaveEvent event) { fireEvent(event); }
    @EventHandler
    public void onWorldUnloadEvent(WorldUnloadEvent event) { fireEvent(event); }*/
}
