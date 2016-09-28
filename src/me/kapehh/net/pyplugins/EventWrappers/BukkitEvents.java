package me.kapehh.net.pyplugins.eventwrappers;

import me.kapehh.net.pyplugins.Main;
import me.kapehh.net.pyplugins.core.PyPluginInstance;
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
public class BukkitEvents implements Listener {
    private Main main;

    public BukkitEvents(Main main) {
        this.main = main;
    }

    private void fireEvent(Event event) {
        // Вызываем во всех плагинах событие, если оно конечно обрабатывается плагином
        for (PyPluginInstance pluginInstance : main.getPyPluginInstances())
            if (pluginInstance.getBukkitListener() != null)
                pluginInstance.getBukkitListener().fireEvent(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreakEvent(BlockBreakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBurnEvent(BlockBurnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockCanBuildEvent(BlockCanBuildEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockDamageEvent(BlockDamageEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockDispenseEvent(BlockDispenseEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockExpEvent(BlockExpEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockFadeEvent(BlockFadeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockFormEvent(BlockFormEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockFromToEvent(BlockFromToEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockGrowEvent(BlockGrowEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockIgniteEvent(BlockIgniteEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockMultiPlaceEvent(BlockMultiPlaceEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPhysicsEvent(BlockPhysicsEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlaceEvent(BlockPlaceEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockRedstoneEvent(BlockRedstoneEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockSpreadEvent(BlockSpreadEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityBlockFormEvent(EntityBlockFormEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLeavesDecayEvent(LeavesDecayEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onNotePlayEvent(NotePlayEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSignChangeEvent(SignChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEnchantItemEvent(EnchantItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPrepareItemEnchantEvent(PrepareItemEnchantEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCreatureSpawnEvent(CreatureSpawnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCreeperPowerEvent(CreeperPowerEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityBreakDoorEvent(EntityBreakDoorEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityChangeBlockEvent(EntityChangeBlockEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityCombustEvent(EntityCombustEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityCreatePortalEvent(EntityCreatePortalEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamageEvent(EntityDamageEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDeathEvent(EntityDeathEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityExplodeEvent(EntityExplodeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityInteractEvent(EntityInteractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityPortalEnterEvent(EntityPortalEnterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityPortalEvent(EntityPortalEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityPortalExitEvent(EntityPortalExitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityShootBowEvent(EntityShootBowEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityTameEvent(EntityTameEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityTargetEvent(EntityTargetEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityTeleportEvent(EntityTeleportEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityUnleashEvent(EntityUnleashEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExpBottleEvent(ExpBottleEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExplosionPrimeEvent(ExplosionPrimeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHorseJumpEvent(HorseJumpEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemDespawnEvent(ItemDespawnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemSpawnEvent(ItemSpawnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPigZapEvent(PigZapEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDeathEvent(PlayerDeathEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerLeashEntityEvent(PlayerLeashEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPotionSplashEvent(PotionSplashEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onProjectileHitEvent(ProjectileHitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onProjectileLaunchEvent(ProjectileLaunchEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSheepDyeWoolEvent(SheepDyeWoolEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSheepRegrowWoolEvent(SheepRegrowWoolEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSlimeSplitEvent(SlimeSplitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHangingBreakEvent(HangingBreakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHangingPlaceEvent(HangingPlaceEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBrewEvent(BrewEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCraftItemEvent(CraftItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFurnaceBurnEvent(FurnaceBurnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFurnaceExtractEvent(FurnaceExtractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFurnaceSmeltEvent(FurnaceSmeltEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryClickEvent(InventoryClickEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryCloseEvent(InventoryCloseEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryCreativeEvent(InventoryCreativeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryDragEvent(InventoryDragEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryEvent(InventoryEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryInteractEvent(InventoryInteractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryOpenEvent(InventoryOpenEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryPickupItemEvent(InventoryPickupItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPaintingBreakEvent(PaintingBreakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPaintingPlaceEvent(PaintingPlaceEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerAnimationEvent(PlayerAnimationEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerBucketFillEvent(PlayerBucketFillEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChannelEvent(PlayerChannelEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChatEvent(PlayerChatEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChatTabCompleteEvent(PlayerChatTabCompleteEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerEditBookEvent(PlayerEditBookEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerEggThrowEvent(PlayerEggThrowEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerExpChangeEvent(PlayerExpChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerFishEvent(PlayerFishEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteractEvent(PlayerInteractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInventoryEvent(PlayerInventoryEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerItemBreakEvent(PlayerItemBreakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerJoinEvent(PlayerJoinEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerKickEvent(PlayerKickEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerLoginEvent(PlayerLoginEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerMoveEvent(PlayerMoveEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerPortalEvent(PlayerPortalEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerPreLoginEvent(PlayerPreLoginEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerQuitEvent(PlayerQuitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerRegisterChannelEvent(PlayerRegisterChannelEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerShearEntityEvent(PlayerShearEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerTeleportEvent(PlayerTeleportEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerUnleashEntityEvent(PlayerUnleashEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerUnregisterChannelEvent(PlayerUnregisterChannelEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerVelocityEvent(PlayerVelocityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onMapInitializeEvent(MapInitializeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPluginDisableEvent(PluginDisableEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPluginEnableEvent(PluginEnableEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onRemoteServerCommandEvent(RemoteServerCommandEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onServerCommandEvent(ServerCommandEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onServerListPingEvent(ServerListPingEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onServiceRegisterEvent(ServiceRegisterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onServiceUnregisterEvent(ServiceUnregisterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onVehicleBlockCollisionEvent(VehicleBlockCollisionEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onVehicleCreateEvent(VehicleCreateEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onVehicleDamageEvent(VehicleDamageEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onVehicleDestroyEvent(VehicleDestroyEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onVehicleEnterEvent(VehicleEnterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onVehicleEntityCollisionEvent(VehicleEntityCollisionEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onVehicleExitEvent(VehicleExitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onVehicleMoveEvent(VehicleMoveEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onVehicleUpdateEvent(VehicleUpdateEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLightningStrikeEvent(LightningStrikeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onThunderChangeEvent(ThunderChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWeatherChangeEvent(WeatherChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChunkLoadEvent(ChunkLoadEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChunkPopulateEvent(ChunkPopulateEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChunkUnloadEvent(ChunkUnloadEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPortalCreateEvent(PortalCreateEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSpawnChangeEvent(SpawnChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onStructureGrowEvent(StructureGrowEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldInitEvent(WorldInitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldLoadEvent(WorldLoadEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldSaveEvent(WorldSaveEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldUnloadEvent(WorldUnloadEvent event) { fireEvent(event); }
}
