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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreakEvent(BlockBreakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBurnEvent(BlockBurnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockCanBuildEvent(BlockCanBuildEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockDamageEvent(BlockDamageEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockDispenseEvent(BlockDispenseEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockExpEvent(BlockExpEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockFadeEvent(BlockFadeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockFormEvent(BlockFormEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockFromToEvent(BlockFromToEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockGrowEvent(BlockGrowEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockIgniteEvent(BlockIgniteEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockMultiPlaceEvent(BlockMultiPlaceEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPhysicsEvent(BlockPhysicsEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlaceEvent(BlockPlaceEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockRedstoneEvent(BlockRedstoneEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockSpreadEvent(BlockSpreadEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityBlockFormEvent(EntityBlockFormEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeavesDecayEvent(LeavesDecayEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onNotePlayEvent(NotePlayEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSignChangeEvent(SignChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnchantItemEvent(EnchantItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareItemEnchantEvent(PrepareItemEnchantEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreatureSpawnEvent(CreatureSpawnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreeperPowerEvent(CreeperPowerEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityBreakDoorEvent(EntityBreakDoorEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityChangeBlockEvent(EntityChangeBlockEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityCombustEvent(EntityCombustEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityCreatePortalEvent(EntityCreatePortalEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageEvent(EntityDamageEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDeathEvent(EntityDeathEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityExplodeEvent(EntityExplodeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityInteractEvent(EntityInteractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityPortalEnterEvent(EntityPortalEnterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityPortalEvent(EntityPortalEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityPortalExitEvent(EntityPortalExitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityShootBowEvent(EntityShootBowEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityTameEvent(EntityTameEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityTargetEvent(EntityTargetEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityTeleportEvent(EntityTeleportEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityUnleashEvent(EntityUnleashEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExpBottleEvent(ExpBottleEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExplosionPrimeEvent(ExplosionPrimeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHorseJumpEvent(HorseJumpEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDespawnEvent(ItemDespawnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemSpawnEvent(ItemSpawnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPigZapEvent(PigZapEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeathEvent(PlayerDeathEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeashEntityEvent(PlayerLeashEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPotionSplashEvent(PotionSplashEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProjectileHitEvent(ProjectileHitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProjectileLaunchEvent(ProjectileLaunchEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSheepDyeWoolEvent(SheepDyeWoolEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSheepRegrowWoolEvent(SheepRegrowWoolEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSlimeSplitEvent(SlimeSplitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHangingBreakEvent(HangingBreakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHangingPlaceEvent(HangingPlaceEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBrewEvent(BrewEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCraftItemEvent(CraftItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFurnaceBurnEvent(FurnaceBurnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFurnaceExtractEvent(FurnaceExtractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFurnaceSmeltEvent(FurnaceSmeltEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClickEvent(InventoryClickEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryCloseEvent(InventoryCloseEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryCreativeEvent(InventoryCreativeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryDragEvent(InventoryDragEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryEvent(InventoryEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryInteractEvent(InventoryInteractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpenEvent(InventoryOpenEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryPickupItemEvent(InventoryPickupItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPaintingBreakEvent(PaintingBreakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPaintingPlaceEvent(PaintingPlaceEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerAnimationEvent(PlayerAnimationEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBucketFillEvent(PlayerBucketFillEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChannelEvent(PlayerChannelEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChatEvent(PlayerChatEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChatTabCompleteEvent(PlayerChatTabCompleteEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerEditBookEvent(PlayerEditBookEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerEggThrowEvent(PlayerEggThrowEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerExpChangeEvent(PlayerExpChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFishEvent(PlayerFishEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEvent(PlayerInteractEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInventoryEvent(PlayerInventoryEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemBreakEvent(PlayerItemBreakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoinEvent(PlayerJoinEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKickEvent(PlayerKickEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLoginEvent(PlayerLoginEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMoveEvent(PlayerMoveEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPortalEvent(PlayerPortalEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLoginEvent(PlayerPreLoginEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuitEvent(PlayerQuitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRegisterChannelEvent(PlayerRegisterChannelEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerShearEntityEvent(PlayerShearEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTeleportEvent(PlayerTeleportEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerUnleashEntityEvent(PlayerUnleashEntityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerUnregisterChannelEvent(PlayerUnregisterChannelEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerVelocityEvent(PlayerVelocityEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMapInitializeEvent(MapInitializeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPluginDisableEvent(PluginDisableEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPluginEnableEvent(PluginEnableEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRemoteServerCommandEvent(RemoteServerCommandEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerCommandEvent(ServerCommandEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerListPingEvent(ServerListPingEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServiceRegisterEvent(ServiceRegisterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServiceUnregisterEvent(ServiceUnregisterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleBlockCollisionEvent(VehicleBlockCollisionEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleCreateEvent(VehicleCreateEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleDamageEvent(VehicleDamageEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleDestroyEvent(VehicleDestroyEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleEnterEvent(VehicleEnterEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleEntityCollisionEvent(VehicleEntityCollisionEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleExitEvent(VehicleExitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleMoveEvent(VehicleMoveEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleUpdateEvent(VehicleUpdateEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLightningStrikeEvent(LightningStrikeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onThunderChangeEvent(ThunderChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChangeEvent(WeatherChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChunkLoadEvent(ChunkLoadEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChunkPopulateEvent(ChunkPopulateEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChunkUnloadEvent(ChunkUnloadEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPortalCreateEvent(PortalCreateEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSpawnChangeEvent(SpawnChangeEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onStructureGrowEvent(StructureGrowEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorldInitEvent(WorldInitEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorldLoadEvent(WorldLoadEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorldSaveEvent(WorldSaveEvent event) { fireEvent(event); }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorldUnloadEvent(WorldUnloadEvent event) { fireEvent(event); }
}
